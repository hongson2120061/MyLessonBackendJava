package com.javaweb.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

// tra response tu server ve cho client
public class BuildingResponseDTO {
	private String name;
	private Long numberOfBasement;
	@JsonProperty(value = "address_building")
	private String address;
	private String managerName;
	private String managerPhoneNumber;
	private Long floorArea;
	private Long emptyArea;
	private String rentedArea;
	private Long rentPrice;
	private String serviceFee;
	private Long brokerageFee;

	public String getRentedArea() {
		return rentedArea;
	}

	public void setRentedArea(String rentedArea) {
		this.rentedArea = rentedArea;
	}

	public String getName() {
		return name;
	}

	public Long getNumberOfBasement() {
		return numberOfBasement;
	}

	public String getAddress() {
		return address;
	}

	public String getManagerName() {
		return managerName;
	}

	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}

	public Long getFloorArea() {
		return floorArea;
	}

	public Long getEmptyArea() {
		return emptyArea;
	}

	public Long getRentPrice() {
		return rentPrice;
	}

	public String getServiceFee() {
		return serviceFee;
	}

	public Long getBrokerageFee() {
		return brokerageFee;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumberOfBasement(Long numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public void setManagerPhoneNumber(String managerPhoneNumber) {
		this.managerPhoneNumber = managerPhoneNumber;
	}

	public void setFloorArea(Long floorArea) {
		this.floorArea = floorArea;
	}

	public void setEmptyArea(Long emptyArea) {
		this.emptyArea = emptyArea;
	}

	public void setRentPrice(Long rentPrice) {
		this.rentPrice = rentPrice;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

	public void setBrokerageFee(Long brokerageFee) {
		this.brokerageFee = brokerageFee;
	}

}
