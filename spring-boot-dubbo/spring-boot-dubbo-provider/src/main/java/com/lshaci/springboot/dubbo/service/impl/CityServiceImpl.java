package com.lshaci.springboot.dubbo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.lshaci.springboot.dubbo.domain.City;
import com.lshaci.springboot.dubbo.mapper.CityMapper;
import com.lshaci.springboot.dubbo.service.CityService;

@Service(version = "1.0.0")
public class CityServiceImpl implements CityService {
	
	@Autowired
	private CityMapper cityMapper;

	@Override
	public int save(City city) {
		return cityMapper.save(city);
	}

	@Override
	public int update(City city) {
		return cityMapper.update(city);
	}

	@Override
	public City findOne(Long id) {
		return cityMapper.findOne(id);
	}

	@Override
	public City findByName(String name) {
		return cityMapper.findByName(name);
	}

}
