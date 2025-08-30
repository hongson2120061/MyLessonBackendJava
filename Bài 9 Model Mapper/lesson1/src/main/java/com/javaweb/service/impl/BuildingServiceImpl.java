package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.dto.response.BuildingResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private BuildingConverter buildingConverter;

	@Override
	public List<BuildingResponseDTO> findAll(Map<String, Object> params, List<String> typecode) {
		// TODO Auto-generated method stub
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(params, typecode);
		List<BuildingResponseDTO> results = new ArrayList<>();
		for (BuildingEntity buildingEntity : buildingEntities) {
			BuildingResponseDTO buildingResponse = buildingConverter.toBuildingSearchResponseDTO(buildingEntity);
			results.add(buildingResponse);
		}
		return results;
	}

}
