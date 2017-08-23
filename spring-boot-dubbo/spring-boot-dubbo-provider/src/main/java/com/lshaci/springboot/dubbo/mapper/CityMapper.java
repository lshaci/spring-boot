package com.lshaci.springboot.dubbo.mapper;

import com.lshaci.springboot.dubbo.domain.City;


public interface CityMapper {
	

	/**
	 * 保存一个对象
	 * 
	 * @param city		需要保存的对象
	 * @return		返回受影响的数据条数
	 */
	int save(City city);
	
	/**
	 * 根据主键修改一个对象
	 * 
	 * @param city		需要修改的对象
	 * @return		返回受影响的数据条数
	 */
	int update(City city);
	
	/**
	 * 根据主键查询一个对象
	 * 
	 * @param id	需要查询对象的主键id
	 * @return		返回查询到的对象
	 */
	City findOne(Long id);
	
	/**
	 * 根据城市名称查询一个城市
	 * 
	 * @param name	城市名称
	 * @return		返回查询到的对象
	 */
	City findByName(String name);

}
