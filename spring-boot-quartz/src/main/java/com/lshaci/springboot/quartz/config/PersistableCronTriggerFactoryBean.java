package com.lshaci.springboot.quartz.config;

import java.text.ParseException;

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

/**
 * 配置触发时间
 */
public class PersistableCronTriggerFactoryBean extends CronTriggerFactoryBean {

	@Override
	public void afterPropertiesSet() throws ParseException {
		super.afterPropertiesSet();
		getJobDataMap().remove(getObject().getJobKey().getName());
	}
}
