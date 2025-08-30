package com.javaweb.repository.entity;

import java.time.LocalDate;

public class AssignmentCustomerEntity {
	private Long id;
	private Long staffId;
	private Long customerId;
	private LocalDate createdDate;
	private LocalDate modifiedDate;
	private String createdBy;
	private String modifiedBy;

	public Long getId() {
		return id;
	}

	public Long getStaffId() {
		return staffId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}