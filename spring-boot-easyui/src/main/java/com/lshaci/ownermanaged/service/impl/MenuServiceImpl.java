package com.lshaci.ownermanaged.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lshaci.ownermanaged.domain.Menu;
import com.lshaci.ownermanaged.mapper.MenuMapper;
import com.lshaci.ownermanaged.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuMapper mapper;
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void save(Menu menu) {
		mapper.save(menu);
	}
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void delete(Long id) {
		mapper.delete(id);
		
	}

	@Override
	public List<Menu> getFirstMenus() {
		return mapper.getFirstMenus();
	}

	@Override
	public List<Menu> getChildrenMenus(Long parentId) {
		return mapper.getChildrenMenus(parentId);
	}

}
