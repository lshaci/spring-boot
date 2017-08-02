package com.lshaci.ownermanaged.plugin;

import java.util.List;

public class PageInfo<T> {

	private int start = 1; // 开始页数(默认1)
	private int pgCt; // 当前页
	private int pgSz; // 每页数据条数
	private int end; // 总页数
	private int total; // 总数据条数
	private List<T> results;// 结果集

	public PageInfo(List<T> list) {
		if (list instanceof PageList) {
			PageList<T> pageList = (PageList<T>) list;
			
			this.pgCt = pageList.getPgCt();
			this.pgSz = pageList.getPgSz();
			this.end = pageList.getEnd();
			this.total = pageList.getTotal();
			this.results = pageList.getResults();
		} else {
			this.pgCt = 1;
			this.pgSz = -1;
			this.end = 1;
			this.total = list.size();
			this.results = list;
		}
	}

	public int getStart() {
		return start;
	}

	public int getPgCt() {
		return pgCt;
	}

	public int getPgSz() {
		return pgSz;
	}

	public int getEnd() {
		return end;
	}

	public int getTotal() {
		return total;
	}

	public List<T> getResults() {
		return results;
	}

}
