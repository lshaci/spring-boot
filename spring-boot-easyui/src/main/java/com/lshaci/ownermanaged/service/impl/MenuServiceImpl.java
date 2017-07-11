package com.lshaci.ownermanaged.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lshaci.ownermanaged.domain.Menu;
import com.lshaci.ownermanaged.mapper.MenuMapper;
import com.lshaci.ownermanaged.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuMapper mapper;

	@Override
	public List<Menu> getFirstMenus() {
		return mapper.getFirstMenus();
	}

	@Override
	public List<Menu> getChildrenMenus(Long parentId) {
		return mapper.getChildrenMenus(parentId);
	}

}
