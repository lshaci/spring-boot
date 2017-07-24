package com.lshaci.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lshaci.domain.City;
import com.lshaci.mapper.CityMapper;
import com.lshaci.service.CityService;

@Service
public class CityServiceImpl extends BaseRedisCrudService<City, Long> implements CityService {
	
	@Autowired
	private CityMapper cityMapper;
	
	@Override
	public City findByName(String name) {
		return cityMapper.findByName(name);
	}


}
