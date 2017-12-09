package com.lshaci.springboot.rabbitmq.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import lombok.extern.slf4j.Slf4j;

@Configuration
@PropertySource("classpath:config/properties/rabbitmq.properties")
@Slf4j
public class RabbitMQConfig {

//	@Bean
//	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//	// 必须是prototype类型, 如果为单例的话，那回调就是最后设置的内容
//	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//		log.info("Init rabbitTemplate whit scope is prototype.");
//		
//		return new RabbitTemplate(connectionFactory);
//	}

}
