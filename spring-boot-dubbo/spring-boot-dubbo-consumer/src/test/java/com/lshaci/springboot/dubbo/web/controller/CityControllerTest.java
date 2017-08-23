package com.lshaci.springboot.dubbo.web.controller;

import org.junit.Test;

import com.lshaci.springboot.dubbo.AbsSpringBaseTest;
import com.lshaci.springboot.dubbo.domain.City;

public class CityControllerTest extends AbsSpringBaseTest<CityController> {

	@Test
	public void testFindOne() {
		City city = bean.findOne(1L);
		System.err.println(city);
	}

}
