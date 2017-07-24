package com.lshaci.service.impl;

import org.springframework.stereotype.Service;

import com.lshaci.annotation.Log;
import com.lshaci.service.AopTestService;

@Service
public class AopTestServiceImpl implements AopTestService {
	
	@Log("方法1")
	public void method1() {
		System.out.println("AopTestServiceImpl.method1()");
	}
	
	public void method2() {
		System.out.println("AopTestServiceImpl.method2()");
	}

}
