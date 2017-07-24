package com.lshaci.java8.domain;

public class HouseOrder {
	
	private String orderNo;
	private String siteCode;
	private String siteName;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	@Override
	public String toString() {
		return "HouseOrder [orderNo=" + orderNo + ", siteCode=" + siteCode + ", siteName=" + siteName + "]";
	}

	
	
}
