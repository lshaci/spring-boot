package com.lshaci.service;

import com.lshaci.domain.City;

public interface CityService extends BaseCrudService<City, Long> {
	
	/**
	 * 根据城市名称查询一个城市
	 * 
	 * @param name		需要查询城市的名称
	 * @return
	 */
	City findByName(String name);

}
