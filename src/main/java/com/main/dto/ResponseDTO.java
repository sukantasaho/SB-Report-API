package com.main.dto;

public class ResponseDTO {
	private Integer eligibleId;
	private String name;
	private String email;
	private Long mobile;
	private Character gender;
	private Long ssn;
	
	public Integer getEligibleId() {
		return eligibleId;
	}
	public void setEligibleId(Integer eligibleId) {
		this.eligibleId = eligibleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public Character getGender() {
		return gender;
	}
	public void setGender(Character gender) {
		this.gender = gender;
	}
	public Long getSsn() {
		return ssn;
	}
	public void setSsn(Long ssn) {
		this.ssn = ssn;
	}
	
	
}
