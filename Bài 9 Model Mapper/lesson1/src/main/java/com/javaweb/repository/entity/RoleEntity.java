package com.javaweb.repository.entity;

import java.time.LocalDate;

public class RoleEntity {
	private Long id;
	private String name;
	private String code;
	private LocalDate createdDate;
	private LocalDate modifiedDate;
	private String createdBy;
	private String modifiedBy;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
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