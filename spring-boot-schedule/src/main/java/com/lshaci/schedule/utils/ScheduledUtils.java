package com.lshaci.schedule.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lshaci.schedule.annotation.TimerTask;
import com.lshaci.schedule.service.ScheduleConfigService;

@Aspect
@Component
public class ScheduledUtils {
	
	@Autowired
	private ScheduleConfigService scheduleConfigService;
	
	@Pointcut("execution(* com.lshaci.schedule.job..*.*(..))")
	public void pointCut() {
		
	}
	
	@Around("pointCut() && @annotation(timerTask)")
	public void invoke(ProceedingJoinPoint joinPoint, TimerTask timerTask) {
		boolean execute = scheduleConfigService.judgeCurrentTask(timerTask);
		if (execute) {
			try {
				joinPoint.proceed();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scheduleConfigService.update(timerTask.value(), 0);
		}
	}

}
