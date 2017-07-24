package com.lshaci.service;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.pool.DruidDataSource;
import com.lshaci.SpringBaseTest;
import com.lshaci.domain.City;

public class CityServiceTest extends SpringBaseTest {
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testFindByName() {
		City findByName = cityService.findByName("成都");
		System.out.println(findByName.getId());
	}

	@Test
	public void testSave() {
		City city = new City();
		city.setName("绵阳");
		city.setState("3");
		cityService.saveOrUpdate(city);
		System.out.println(city.getId());
	}

	@Test
	public void testDeleteT() {
		DruidDataSource d = (DruidDataSource) dataSource;
		System.out.println(d.getMaxActive());
	}

	@Test
	public void testDeleteID() {
		cityService.delete(1L);
	}

	@Test
	public void testFindOne() {
		City city = cityService.findOne(5L);
		System.out.println(city);
	}

	@Test
	public void testFindAll() {
		
	}

}
