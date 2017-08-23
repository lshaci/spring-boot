package com.lshaci.springboot.dubbo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lshaci.springboot.dubbo.domain.City;
import com.lshaci.springboot.dubbo.service.CityService;

@Controller
@RequestMapping("/city/")
public class CityController {
	
	@Reference(version = "1.0.0")
	private CityService cityService;
	
	@RequestMapping("findOne")
	@ResponseBody
	public City findOne(Long id) {
		return cityService.findOne(id);
	}

}
