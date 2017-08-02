package com.lshaci.ownermanaged.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.lshaci.ownermanaged.plugin.PageHelper;

@Configuration
@MapperScan("com.lshaci.ownermanaged.mapper")
public class MybatisConfig {

	private static final Logger logger = LoggerFactory.getLogger(MybatisConfig.class);

	@Autowired
	private DataSource dataSource;
	
	@Value("${mybatis.mapper-locations}")
	private String[] mapperLocations;
	
	@Value("${mybatis.type-aliases-package}")
	private String typeAliasesPackage;

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean() {
		logger.debug("Init Mybatis SqlSessionFactory...");

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(resolveMapperLocations());
		sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
		// 分页插件
		PageHelper pageHelper = new PageHelper();
		// Properties props = new Properties();
		// props.setProperty("reasonable", "true");
		// props.setProperty("supportMethodsArguments", "true");
		// props.setProperty("returnPageInfo", "check");
		// props.setProperty("params", "count=countSql");
		// pageHelper.setProperties(props);
		// 添加插件
		sqlSessionFactoryBean.setPlugins(new Interceptor[] { pageHelper });
		try {
			return sqlSessionFactoryBean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Resource[] resolveMapperLocations() {
		ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
		List<Resource> resources = new ArrayList<Resource>();
		if (this.mapperLocations != null) {
			for (String mapperLocation : this.mapperLocations) {
				try {
					Resource[] mappers = resourceResolver.getResources(mapperLocation);
					resources.addAll(Arrays.asList(mappers));
				} catch (IOException e) {
					// ignore
				}
			}
		}
		return resources.toArray(new Resource[resources.size()]);
	}

}
