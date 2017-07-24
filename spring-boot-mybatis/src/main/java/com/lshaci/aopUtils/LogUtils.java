package com.lshaci.aopUtils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.lshaci.annotation.Log;


@Aspect
@Component
public class LogUtils {
	
	@Pointcut("execution(* com.lshaci.service..*.*(..))")
	public void pointCut() {
		
	}
	
	@Before("pointCut() && @annotation(log)")
	public void invoke(JoinPoint joinPoint, Log log) {
		System.out.println("=========执行方法前========");
		
		System.out.println(log.value());
	}

}
