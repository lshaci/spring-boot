package com.lshaci.query;

/**
 * 公共的查询对象
 * @author lshaci
 */
public abstract class BaseQuery {
	
	private Integer page = 1;	//当前查询的页
	private Integer rows = 10;	//每页数据条数(即在数据库查询多少行数据)
	
	private String keyword;	//关键字查询，快速搜索
	
	/*
	 * 为字段提供set和get方法
	 */
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getIndex() {
		return (this.page - 1) * this.rows;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
