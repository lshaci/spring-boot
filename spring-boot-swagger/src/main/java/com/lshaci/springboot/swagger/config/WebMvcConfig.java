package com.lshaci.springboot.swagger.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * web mvc config
 * 继承WebMvcConfigurerAdapter不会覆盖spring boot的自动配置
 * 继承WebMvcConfigurationSupport会覆盖spring boot的自动配置
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

	/**
	 * Spring Boot自动配置本身不会自动把/swagger-ui.html这个路径映射到对应的目录META-INF/resources/下面。我们加上这个映射即可
	 * 
	 * 使用spring boot(1.5.3)和swagger(2.7.0)不需要以下配置
	 */
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		logger.debug("Add swagger-ui.html to ResourceHandler");
		
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        logger.debug("Add /webjars/** to ResourceHandler");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}
