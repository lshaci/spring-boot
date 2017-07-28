package com.lshaci.schedule.config;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@MapperScan("com.lshaci.schedule.mapper")
@PropertySource("classpath:config/properties/datasource.properties")
public class DataSourceConfig {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

	@Bean
	@ConfigurationProperties(prefix = "druid.datasource")
	public DataSource dataSource() {
		logger.debug("Init DataSource use DruidDataSource...");

		return new DruidDataSource();
	}
	
	@Autowired
	private DataSource dataSource;

	@Bean
	public PlatformTransactionManager transactionManager() {
		logger.debug("Init TransactionManager...");
		
		return new DataSourceTransactionManager(dataSource);
	}

}
