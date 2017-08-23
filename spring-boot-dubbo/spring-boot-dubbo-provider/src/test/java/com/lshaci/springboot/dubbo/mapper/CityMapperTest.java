package com.lshaci.springboot.dubbo.mapper;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lshaci.springboot.dubbo.AbsSpringBaseTest;
import com.lshaci.springboot.dubbo.domain.City;

public class CityMapperTest extends AbsSpringBaseTest<CityMapper> {

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindOne() {
		City city = bean.findOne(1L);
		System.out.println(city);
	}

	@Test
	public void testFindByName() {
		fail("Not yet implemented");
	}

}
