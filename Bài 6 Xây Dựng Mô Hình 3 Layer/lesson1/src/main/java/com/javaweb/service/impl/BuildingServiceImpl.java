package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.dto.response.BuildingResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.impl.BuildingRepositoryImpl;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingRepository buildingRepository;

	@Override
	public List<BuildingResponseDTO> findAll(String nameBuilding, Long numberOfBasement) {
		// TODO Auto-generated method stub
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(nameBuilding, numberOfBasement);
		List<BuildingResponseDTO> results = new ArrayList<>();
		for (BuildingEntity buildingEntity : buildingEntities) {
			BuildingResponseDTO buildingResponse = new BuildingResponseDTO();
			buildingResponse.setId(buildingEntity.getId());
			buildingResponse.setName(buildingEntity.getName());
			buildingResponse.setNumberOfBasement(buildingEntity.getNumberOfBasement());
			buildingResponse.setRentPrice(buildingEntity.getRentPrice());
			buildingResponse.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + "Quan "
					+ buildingEntity.getDistrictId());
			results.add(buildingResponse);
		}

		return results;
	}

}
