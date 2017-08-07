package com.lshaci.ownermanaged.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.lshaci.ownermanaged.web.converter.String2DateConverter;
import com.lshaci.ownermanaged.web.interceptor.TestInterceptor;

/**
 * web mvc config
 * 继承WebMvcConfigurerAdapter不会覆盖spring boot的自动配置
 * 继承WebMvcConfigurationSupport会覆盖spring boot的自动配置
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);
	
	@Autowired
	private RequestMappingHandlerAdapter handlerAdapter;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.debug("Add Interceptor, add TestInterceptor.");
		
		registry.addInterceptor(new TestInterceptor());
	}
	
	@PostConstruct
	public void addConverter() {
		ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
		
		if (initializer.getConversionService() != null) {
			GenericConversionService conversionService = (GenericConversionService) initializer.getConversionService();
			logger.debug("Add Converter, add String2DateConverter.");
			
			conversionService.addConverter(new String2DateConverter());
		}
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		logger.debug("Add fastJsonHttpMessageConverter to messageConverters.");
		
		converters.add(fastJsonHttpMessageConverter());
	}
	
	@Bean(name = "multipartResolver")  
    public CommonsMultipartResolver commonsMultipartResolver(){  
        logger.info("CommonsMultipartResolver.");
        
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        
        multipartResolver.setDefaultEncoding("UTF-8");
        multipartResolver.setMaxUploadSize(10485760);
        
        return multipartResolver;  
    }
	
	@Bean
	public RequestContextListener requestContextListener(){
		logger.info("RequestContextListener.");
		
	    return new RequestContextListener();
	}
	
	private FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter() {
		logger.debug("Init fastJsonHttpMessageConverter.");
		FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter4();
		
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);	// 等价于MediaType.parseMediaType("application/json;charset=UTF-8")
		supportedMediaTypes.add(MediaType.parseMediaType("text/html;charset=UTF-8"));
		
		fastJsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
		fastJsonHttpMessageConverter.setFastJsonConfig(getFastJsonConfig());
		
		return fastJsonHttpMessageConverter;
	}
	
	private FastJsonConfig getFastJsonConfig() {
		logger.debug("Init FastJsonConfig.");
		
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		
		List<SerializerFeature> serializerFeatures = new ArrayList<>();
		serializerFeatures.add(SerializerFeature.PrettyFormat);
		serializerFeatures.add(SerializerFeature.WriteMapNullValue);
		serializerFeatures.add(SerializerFeature.WriteNullStringAsEmpty);
		
		SerializerFeature[] features = serializerFeatures.toArray(new SerializerFeature[serializerFeatures.size()]);
		fastJsonConfig.setSerializerFeatures(features);
		
		return fastJsonConfig;
	}

}
