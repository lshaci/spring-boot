package com.lshaci.ownermanaged.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lshaci.ownermanaged.domain.Menu;
import com.lshaci.ownermanaged.plugin.PageHelper;
import com.lshaci.ownermanaged.plugin.PageInfo;
import com.lshaci.ownermanaged.service.MenuService;

@RestController
@RequestMapping("/menu/")
public class MenuController {
	
	@Autowired
	private MenuService service;

	@RequestMapping("getMenus/{id}/{pgCt}/{pgSz}")
	public Object getMenus(@PathVariable("id") Long id, @PathVariable("pgCt") int pgCt, @PathVariable("pgSz") int pgSz) {
		PageHelper.setPage(pgCt, pgSz);
		if (id == null) {
			return service.getFirstMenus();
		} else {
			List<Menu> childrenMenus = service.getChildrenMenus(id);
			System.err.println(childrenMenus);
			return new PageInfo<Menu>(childrenMenus);
		}
	}
	
}
