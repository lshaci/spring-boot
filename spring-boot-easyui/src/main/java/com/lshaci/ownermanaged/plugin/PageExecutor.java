package com.lshaci.ownermanaged.plugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

public class PageExecutor implements Executor {
	
	private Executor executor;
	
	public PageExecutor(Executor executor) {
        this.executor = executor;
    }

	@Override
	public int update(MappedStatement ms, Object parameter) throws SQLException {
		return executor.update(ms, parameter);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler,
			CacheKey cacheKey, BoundSql boundSql) throws SQLException {
        List<E> results = executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
        int total = getTotal(ms, parameter);
        return new PageList<E>(PageHelper.getPgCt(), PageHelper.getPgSz(), total, results);
	}
	
	
	private int getTotal(MappedStatement ms, Object parameter) {
        BoundSql bsql = ms.getBoundSql(parameter);
        String sql = bsql.getSql();
        String countSql = getCountSql(sql);
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = ms.getConfiguration().getEnvironment().getDataSource().getConnection();
            stmt = connection.prepareStatement(countSql);
            setParameters(stmt, ms, bsql, parameter);
            rs = stmt.executeQuery();
            if (rs.next())
                return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
	
	@SuppressWarnings("unchecked")
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
            Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    @SuppressWarnings("rawtypes")
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName
                                + " of statement " + mappedStatement.getId());
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }
	
	private String getCountSql(String sql) {
		StringBuffer sb = new StringBuffer("select count(1) from");
		sql = sql.toLowerCase();

		if (sql.lastIndexOf("order") > sql.lastIndexOf(")")) {
			sb.append(sql.substring(sql.indexOf("from") + 4, sql.lastIndexOf("order")));
		} else {
			sb.append(sql.substring(sql.indexOf("from") + 4));
		}
		return sb.toString();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler)
			throws SQLException {
		BoundSql boundSql = ms.getBoundSql(parameter);
		CacheKey createCacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
        return query(ms, parameter, rowBounds, resultHandler, createCacheKey, boundSql);
	}

	@Override
	public <E> Cursor<E> queryCursor(MappedStatement ms, Object parameter, RowBounds rowBounds) throws SQLException {
		return executor.queryCursor(ms, parameter, rowBounds);
	}

	@Override
	public List<BatchResult> flushStatements() throws SQLException {
		return executor.flushStatements();
	}

	@Override
	public void commit(boolean required) throws SQLException {
		executor.commit(required);
	}

	@Override
	public void rollback(boolean required) throws SQLException {
		executor.rollback(required);
	}

	@Override
	public CacheKey createCacheKey(MappedStatement ms, Object parameterObject, RowBounds rowBounds, BoundSql boundSql) {
		return executor.createCacheKey(ms, parameterObject, rowBounds, boundSql);
	}

	@Override
	public boolean isCached(MappedStatement ms, CacheKey key) {
		return executor.isCached(ms, key);
	}

	@Override
	public void clearLocalCache() {
		executor.clearLocalCache();
	}

	@Override
	public void deferLoad(MappedStatement ms, MetaObject resultObject, String property, CacheKey key,
			Class<?> targetType) {
		executor.deferLoad(ms, resultObject, property, key, targetType);
	}

	@Override
	public Transaction getTransaction() {
		return executor.getTransaction();
	}

	@Override
	public void close(boolean forceRollback) {
		executor.close(forceRollback);
	}

	@Override
	public boolean isClosed() {
		return executor.isClosed();
	}

	@Override
	public void setExecutorWrapper(Executor executor) {
		this.executor.setExecutorWrapper(executor);
	}

}
