package com.lshaci.ownermanaged.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lshaci.ownermanaged.domain.Menu;
import com.lshaci.ownermanaged.service.MenuService;

@RestController
@RequestMapping("/menu/")
public class MenuController {
	
	@Autowired
	private MenuService service;

	@RequestMapping("getMenus")
	public List<Menu> getMenus(Long id) {
		if (id == null) {
			return service.getFirstMenus();
		} else {
			return service.getChildrenMenus(id);
		}
	}
	
}
