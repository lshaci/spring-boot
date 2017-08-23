package com.lshaci.springboot.dubbo.domain;

import java.io.Serializable;

public class City implements Serializable {

	private static final long serialVersionUID = 1049902993010400532L;

	private Long id;
	private String name;
	private String state;
	
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", state=" + state + "]";
	}
}
