package com.javaweb.api;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.dto.ErrorDetailResponse;
import com.javaweb.myexception.ValidateDataBuildingException;

@RestController
public class BuildingAPI {
	
	@GetMapping(value = "/api/buildings")
	public Object getBuilding(@RequestParam Map<String, Object> params,
			@RequestParam(name = "typeCode", required = false) List<String> typeCode) {
		BuildingDTO building1 = new BuildingDTO();
		building1.setName("BK H1 Building");
		building1.setNumberOfBasement(3L);
		building1.setRentPrice(2000L);
		building1.setTypeCode(Arrays.asList("tang-tret,nguyen-can"));
		try {
			System.out.println(4/0);
		}
		catch(Exception e) {
			ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse();
			errorDetailResponse.setError(e.getMessage());
			errorDetailResponse.setDetail(Arrays.asList("Khong thể chia cho số 0"));
			return errorDetailResponse;
		}
		BuildingDTO building2 = new BuildingDTO();
		building2.setName("BK H2 Building");
		building2.setNumberOfBasement(4L);
		building2.setRentPrice(1000L);
		building2.setTypeCode(Arrays.asList("tang-tret,nguyen-can,noi-that"));
		return Arrays.asList(building1, building2);
	}
	public static void validateDataBuilding(BuildingDTO buildingDTO) {
		// neu la string thi se kiem tra co null hoac rong khong
		// neu la so thi kiem tra xem co ron null khong
		if(buildingDTO.getName() == null || buildingDTO.getName().isEmpty() || buildingDTO.getNumberOfBasement() == null ) {
			throw new ValidateDataBuildingException("Name or NumberOfBasement Is Null");
		}
	}
	@PostMapping(value = "/api/buildings" )
	public Object   createBuilding(@RequestBody BuildingDTO buildingDTO) {
		try {
			validateDataBuilding(buildingDTO);
			
		}
		catch(Exception e) {
			ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse();
			errorDetailResponse.setError(e.getMessage());
			errorDetailResponse.setDetail(Arrays.asList("Ten hoac so tang da bi null"));
			return errorDetailResponse;
		}
		return buildingDTO;
	}
	@DeleteMapping(value = "/api/buildings/{ids}")
	public void deleteBuilding(@PathVariable List<Long> ids
			) {
		System.out.print("Building Delete id = " + ids );
	}
	
}
