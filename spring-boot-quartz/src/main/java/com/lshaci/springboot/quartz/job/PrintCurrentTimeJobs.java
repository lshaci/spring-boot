package com.lshaci.springboot.quartz.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class PrintCurrentTimeJobs extends QuartzJobBean {
	
	private static final Logger logger = LoggerFactory.getLogger(PrintCurrentTimeJobs.class);
	
	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		System.err.println("任务执行中 --> " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}

}
