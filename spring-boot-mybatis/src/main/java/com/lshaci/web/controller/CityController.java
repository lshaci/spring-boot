package com.lshaci.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lshaci.domain.City;
import com.lshaci.service.CityService;

@Controller
@RequestMapping("/city/")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping("save")
	public void save() {
		City city = new City();
		city.setName("成都");
		city.setState("1");
		cityService.saveOrUpdate(city);
	}
	
	@RequestMapping("find/{name}")
	@ResponseBody
	public City find(@PathVariable("name") String name) {
		return cityService.findByName(name);
	}

}
