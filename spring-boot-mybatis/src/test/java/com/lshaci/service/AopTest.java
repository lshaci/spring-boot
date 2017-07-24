package com.lshaci.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lshaci.SpringBaseTest;

public class AopTest extends SpringBaseTest {
	
	@Autowired
	private AopTestService aopTestService;
	
	@Test
	public void test() {
		aopTestService.method2();
	}

}
