package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.dto.response.BuildingResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private RentAreaRepository rentAreaRepository;

	@Override
	public List<BuildingResponseDTO> findAll(Map<String, Object> params, List<String> typecode) {
		// TODO Auto-generated method stub
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(params, typecode);
		List<BuildingResponseDTO> results = new ArrayList<>();
		for (BuildingEntity buildingEntity : buildingEntities) {
			BuildingResponseDTO buildingResponse = new BuildingResponseDTO();
			buildingResponse.setName(buildingEntity.getName());
			buildingResponse.setNumberOfBasement(buildingEntity.getNumberOfBasement());
			buildingResponse.setRentPrice(buildingEntity.getRentPrice());
			buildingResponse.setManagerName(buildingEntity.getManagerName());
			buildingResponse.setManagerPhoneNumber(buildingEntity.getManagerPhoneNumber());
			buildingResponse.setFloorArea(buildingEntity.getFloorArea());
			buildingResponse.setEmptyArea(null);
			buildingResponse.setRentPrice(buildingEntity.getRentPrice());
			buildingResponse.setServiceFee(buildingEntity.getServiceFee());
			buildingResponse.setBrokerageFee(buildingEntity.getBrokerageFee());
			DistrictEntity districtEntity = districtRepository.findById(buildingEntity.getDistrictId());
			buildingResponse.setAddress(
					buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + districtEntity.getName());
			List<RentAreaEntity> rentAreas = rentAreaRepository.findById(buildingEntity.getId());
			StringBuilder rentAreaValue = new StringBuilder("");
//			for (int i = 0; i < rentAreas.size(); i++) {
//				rentAreaValue.append(rentAreas.get(i).getValue());
//				if (i < rentAreas.size() - 1) {
//					rentAreaValue.append(",");
//				}
//			}
			rentAreaValue.append(rentAreas.stream().map(i -> i.getValue().toString()).collect(Collectors.joining(",")));
			buildingResponse.setRentedArea(rentAreaValue.toString());
			results.add(buildingResponse);
		}
		return results;
	}

}
