package com.javaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.dto.request.BuildingDTO;
import com.javaweb.dto.response.BuildingResponseDTO;
import com.javaweb.myexception.ValidateDataBuildingException;
import com.javaweb.service.impl.BuildingServiceImpl;

@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {
	@Autowired
	private BuildingServiceImpl buildingServiceImpl;

	@GetMapping
	public Object getBuilding(@RequestParam(name = "name", required = false) String nameBuilding,
			@RequestParam(name = "numberOfBasement", required = false) Long numberOfBasement) {
		List<BuildingResponseDTO> results = buildingServiceImpl.findAll(nameBuilding, numberOfBasement);
		return results;
	}

	public static void validateDataBuilding(BuildingDTO buildingDTO) {
		if (buildingDTO.getName() == null || buildingDTO.getName().isEmpty()
				|| buildingDTO.getNumberOfBasement() == null) {
			throw new ValidateDataBuildingException("Name or NumberOfBasement Is Null");
		}
	}

	@PostMapping
	public Object createBuilding(@RequestBody BuildingDTO buildingDTO) {
		validateDataBuilding(buildingDTO);
		return buildingDTO;
	}

	@DeleteMapping("{ids}")
	public void deleteBuilding(@PathVariable List<Long> ids) {
		System.out.print("Building Delete id = " + ids);
	}

}
