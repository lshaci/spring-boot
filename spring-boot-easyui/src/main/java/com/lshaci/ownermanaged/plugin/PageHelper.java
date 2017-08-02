package com.lshaci.ownermanaged.plugin;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * mybatis & mysql 分页插件
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class PageHelper implements Interceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(PageHelper.class);
	
	private static ThreadLocal<Boolean> local = new ThreadLocal<>();

	/**
	 * 当前页
	 */
	private static int pgCt;
	
	/**
	 * 每页数据条数
	 */
	private static int pgSz;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		if (isPage() && invocation.getTarget() instanceof StatementHandler) {
			StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
			MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
			BoundSql boundSql = statementHandler.getBoundSql();
			
			// 重写sql
			String pageSql = concatPageSql(boundSql.getSql());
			logger.debug("The page sql --> " + pageSql);
			
			metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
		}
		
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		if (isPage()) {
			if (Executor.class.isAssignableFrom(target.getClass())) {
				PageExecutor executor = new PageExecutor((Executor) target);
				return Plugin.wrap(executor, this);
			} else if (target instanceof StatementHandler) {
				return Plugin.wrap(target, this);
			}
		}
		return target;
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub

	}
	
	private String concatPageSql(String sql) {
		StringBuffer sb = new StringBuffer(sql);
		sb.append(" limit ").append((pgCt - 1) * pgSz).append(" , ").append(pgSz);
		return sb.toString();
	}

	/**
	 * 设置分页参数
	 * 
	 * @param pgCt	当前页
	 * @param pgSz	每页数据条数
	 */
	public static void setPage(int pgCt, int pgSz) {
		PageHelper.pgCt = pgCt < 1 ? 1 : pgCt;
		PageHelper.pgSz = pgSz <= 0 ? 10 : pgSz;
		needPage();
	}
	
	/**
	 * 设置需要分页
	 */
	private static void needPage() {
		local.set(true);
	}
	
	/**
	 * 判断是否需要分页
	 * 
	 * @return
	 */
	public static boolean isPage() {
		Boolean isPage = local.get();
		if (isPage != null) {
			return true;
		}
		return false;
	}

	public static int getPgCt() {
		return pgCt;
	}

	public static int getPgSz() {
		return pgSz;
	}

}
