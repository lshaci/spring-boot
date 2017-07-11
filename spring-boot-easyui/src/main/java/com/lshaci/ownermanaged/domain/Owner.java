package com.lshaci.ownermanaged.domain;

public class Owner {
	
	private Long id;
	private String username;
	private String password;
	private String name;
	private String qq;
	private String email;
	private String phone;
	private String salt;
	private String proofPic;
	private String proofPicMin;
	private Integer disable;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getProofPic() {
		return proofPic;
	}
	public void setProofPic(String proofPic) {
		this.proofPic = proofPic;
	}
	public String getProofPicMin() {
		return proofPicMin;
	}
	public void setProofPicMin(String proofPicMin) {
		this.proofPicMin = proofPicMin;
	}
	public Integer getDisable() {
		return disable;
	}
	public void setDisable(Integer disable) {
		this.disable = disable;
	}
	
	@Override
	public String toString() {
		return "Owner [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", qq=" + qq
				+ ", email=" + email + ", phone=" + phone + ", salt=" + salt + ", proofPic=" + proofPic
				+ ", proofPicMin=" + proofPicMin + ", disable=" + disable + "]";
	}
	
}
