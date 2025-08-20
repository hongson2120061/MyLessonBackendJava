package com.javaweb.service;

import java.util.List;

import com.javaweb.dto.response.BuildingResponseDTO;

public interface BuildingService {
	List<BuildingResponseDTO> findAll(String nameBuilding, Long numberOfBasement);
}
