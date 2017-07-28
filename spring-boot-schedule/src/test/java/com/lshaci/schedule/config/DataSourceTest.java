package com.lshaci.schedule.config;

import javax.sql.DataSource;

import org.junit.Test;

import com.lshaci.schedule.SpringTest;

public class DataSourceTest extends SpringTest<DataSource> {

	@Test
	public void test() throws Exception {
		System.out.println(t.getClass());
	}
}
