package com.lshaci.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class CopyObjectUtilTest {

	@Test
	public void testObj2Obj() throws Exception {
		TestObj1 obj1 = new TestObj1();
		
		obj1.setId(1L);
		obj1.setName("姓名");
		obj1.setAge(18);
		obj1.setDisable(true);
		obj1.setStatus(0);
		
		Map<String, String> rename = new HashMap<>();
		rename.put("status", "state");
		rename.put("age", "ag");
		Map<String, Object> extra = new HashMap<>();
		extra.put("email", "test@qq.com");
		extra.put("gender", 1);
		List<String> skips = new ArrayList<>();
		skips.add("id");
		skips.add("gender");
		TestObj2 obj2 = CopyObjectUtil.copy(obj1, TestObj2.class, null, extra, skips);
		System.out.println(obj2);
		
	}
}


class TestObj1 implements Serializable {

	private static final long serialVersionUID = 739973388070745940L;
	
	private Long id;
	private String name;
	private Integer age;
	private boolean disable;
	private int status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public boolean isDisable() {
		return disable;
	}
	public void setDisable(boolean disable) {
		this.disable = disable;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "TestObj1 [id=" + id + ", name=" + name + ", age=" + age + ", disable=" + disable + ", status=" + status
				+ "]";
	}
	
}


class TestObj2 {
	private Long id;
	private String name;
	private Integer age;
	private int gender;
	private String email;
	private boolean disable;
	private int state;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isDisable() {
		return disable;
	}
	public void setDisable(boolean disable) {
		this.disable = disable;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "TestObj2 [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", email=" + email
				+ ", disable=" + disable + ", state=" + state + "]";
	}
	
	
}