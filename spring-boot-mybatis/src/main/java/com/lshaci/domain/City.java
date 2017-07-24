package com.lshaci.domain;

public class City extends IDEntity<Long> {
	
	private String name;
	
	private String state;
	
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
		return "City [id=" + super.getId() + ", name=" + name + ", state=" + state + "]";
	}
	
}
