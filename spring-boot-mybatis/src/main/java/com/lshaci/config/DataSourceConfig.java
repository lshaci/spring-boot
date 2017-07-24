package com.lshaci.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
// 加载资源文件
@PropertySource("classpath:config/datasource.properties")
public class DataSourceConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
	
	/**
	 * 声明数据库连接池
	 */
	@Bean(name = "dataSource")
	@ConfigurationProperties("datasource")
	public DataSource dataSource() {
		logger.debug("DataSource");
		
		return new DruidDataSource();
	}
}
