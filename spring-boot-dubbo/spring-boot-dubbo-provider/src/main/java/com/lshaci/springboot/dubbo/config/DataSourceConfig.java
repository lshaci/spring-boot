package com.lshaci.springboot.dubbo.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:config/properties/datasource.properties")
public class DataSourceConfig {

	private final static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

	@Bean
	@ConfigurationProperties(prefix = "druid.datasource")
	public DataSource dataSource() {
		logger.debug("Init DataSource use DruidDataSource...");

		return new DruidDataSource();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		logger.debug("Init TransactionManager...");
		
		return new DataSourceTransactionManager(dataSource());
	}

}
