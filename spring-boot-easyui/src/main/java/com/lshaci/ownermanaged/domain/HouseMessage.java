package com.lshaci.ownermanaged.domain;

public class HouseMessage {
	
	private Long id;
	private Integer buildingNo;
	private Integer unit;
	private Integer floor;
	private Integer houseNo;
	private Integer verify;
	private Long ownerId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(Integer buildingNo) {
		this.buildingNo = buildingNo;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public Integer getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(Integer houseNo) {
		this.houseNo = houseNo;
	}
	public Integer getVerify() {
		return verify;
	}
	public void setVerify(Integer verify) {
		this.verify = verify;
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	
	@Override
	public String toString() {
		return "HouseMessage [id=" + id + ", buildingNo=" + buildingNo + ", unit=" + unit + ", floor=" + floor
				+ ", houseNo=" + houseNo + ", verify=" + verify + ", ownerId=" + ownerId + "]";
	}

	
}
