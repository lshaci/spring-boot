package com.lshaci.springboot.quartz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:config/properties/quartz.properties")
@ImportResource("classpath:config/xml/quartz-spring.xml")
public class QuartzConfig {
	
}
