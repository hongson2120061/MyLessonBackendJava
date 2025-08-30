package com.javaweb.repository.entity;

import java.security.Timestamp;

public class TransactionTypeEntity {
	private Long id;
	private String name;
	private String code;
	private Timestamp createdData;
	private Timestamp modifiedData;
	private String createdBy;
	private String modifiedBy;
	private Long transactionId;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public Timestamp getCreatedData() {
		return createdData;
	}

	public Timestamp getModifiedData() {
		return modifiedData;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCreatedData(Timestamp createdData) {
		this.createdData = createdData;
	}

	public void setModifiedData(Timestamp modifiedData) {
		this.modifiedData = modifiedData;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

}
