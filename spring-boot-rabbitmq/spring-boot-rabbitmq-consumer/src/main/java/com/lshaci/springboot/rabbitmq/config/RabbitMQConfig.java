package com.lshaci.springboot.rabbitmq.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/properties/rabbitmq.properties")
public class RabbitMQConfig {

}
