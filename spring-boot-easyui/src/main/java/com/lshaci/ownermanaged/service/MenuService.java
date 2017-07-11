package com.lshaci.ownermanaged.service;

import java.util.List;

import com.lshaci.ownermanaged.domain.Menu;

public interface MenuService {

	List<Menu> getFirstMenus();

	List<Menu> getChildrenMenus(Long parentId);
}
