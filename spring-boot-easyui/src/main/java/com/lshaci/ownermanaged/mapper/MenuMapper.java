package com.lshaci.ownermanaged.mapper;

import java.util.List;

import com.lshaci.ownermanaged.domain.Menu;

public interface MenuMapper {
	
	void save(Menu menu);
	
	void delete(Long id);
	
	Menu findOne(Long id);
	
	List<Menu> getFirstMenus();

	List<Menu> getChildrenMenus(Long parentId);

}
