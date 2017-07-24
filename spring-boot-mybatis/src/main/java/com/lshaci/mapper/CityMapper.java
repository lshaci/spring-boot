package com.lshaci.mapper;

import com.lshaci.domain.City;

public interface CityMapper extends BaseCrudMapper<City, Long> {

	City findByName(String name);

}
