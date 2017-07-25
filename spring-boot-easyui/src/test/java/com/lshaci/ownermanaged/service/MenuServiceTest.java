package com.lshaci.ownermanaged.service;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.lshaci.ownermanaged.SpringTest;
import com.lshaci.ownermanaged.domain.Menu;

public class MenuServiceTest extends SpringTest<MenuService> {

	@Test
	public void testSave() {
		Menu menu = new Menu();
		menu.setText("测试菜单");
		menu.setState("open");
		t.save(menu);
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFirstMenus() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetChildrenMenus() {
		fail("Not yet implemented");
	}

}
