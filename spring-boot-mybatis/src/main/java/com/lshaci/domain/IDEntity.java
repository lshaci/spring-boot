package com.lshaci.domain;

import java.io.Serializable;

/**
 * 公共的ID实体
 * 
 * @author lshaci
 *
 * @param <ID>		实体主键的类型
 */
public abstract class IDEntity<ID extends Serializable> {

	private ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	
	
}
