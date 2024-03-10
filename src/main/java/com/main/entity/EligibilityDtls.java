package com.main.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.main.config.AppConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ELIGIBILITY_DETAILS")
public class EligibilityDtls {
	
	@Id
	@GeneratedValue
	@Column(name="ELIGIBLE_ID")
	private Integer eligibleId;
	
	@Column(name="NAME", length = 25)
	private String name;
	
	@Column(name = "PLAN_NAME", length = 20)
	private String planName;
	
	@Column(name="MOBILE")
	private Long mobile;
	
	@Column(name = "EMAIL", length = 30)
	private String email;
	
	@Column(name="GENDER")
	private Character gender;
	
	@Column(name="SSN")
	private Long ssn;
	
	@Column(name="PLAN_STATUS", length = 15)
	private String planStatus;
	
	@Column(name="PLAN_START_DATE")
	private LocalDate planStartDate;
	
	@Column(name="PLAN_END_DATE")
	private LocalDate planEndDate;
	
	@Column(name="CREATED_DATE", updatable = false)
  	@CreationTimestamp
	private LocalDate createDate;
	
	@Column(name="MODIFIED_DATE", insertable = false)
  	@UpdateTimestamp
	private LocalDate modifiedDate;
	
	@Column(name="CREATED_BY", length = 25)
	private String createdBy;
	
	@Column(name="MODIFIED_BY", length = 25)
	private String modifiedBy;
	
	@Column(name="RECORD_STATUS", length = 10)
	private String recordStatus=AppConstants.RECORD_STATUS_VALUE;

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

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	public LocalDate getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(LocalDate planStartDate) {
		this.planStartDate = planStartDate;
	}

	public LocalDate getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(LocalDate planEndDate) {
		this.planEndDate = planEndDate;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	

}
