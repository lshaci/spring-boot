package com.lshaci.springboot.activemq.config;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:config/properties/activemq.properties")
public class ActiveMQConfig {
	
	private Logger logger = LoggerFactory.getLogger(ActiveMQConfig.class);
	
	@Value("${activemq.queue.name.test}")
	private String testQueueName;
	
	@Bean
	public Destination testQueue() {
		logger.debug("Init ActiveMQQueue. The queue name is: " + testQueueName);
		
		return new ActiveMQQueue(testQueueName);
	}

}
