package com.javaweb.repository.entity;

import java.time.LocalDate;

public class TransactionEntity {
	private Long id;
	private String note;
	private Long customerId;
	private LocalDate createdDate;
	private LocalDate modifiedDate;
	private String createdBy;
	private String modifiedBy;

	public Long getId() {
		return id;
	}

	public String getNote() {
		return note;
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

	public void setNote(String note) {
		this.note = note;
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
