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

@RestController
public class BuildingAPI {
	// cach 5
	@GetMapping(value = "/api/buildings")
	public Object getBuilding(@RequestParam Map<String, Object> params,
			@RequestParam(name = "typeCode", required = false) List<String> typeCode) {
		BuildingDTO building1 = new BuildingDTO();
		building1.setName("BK H1 Building");
		building1.setNumberOfBasement(3L);
		building1.setRentPrice(2000L);
		building1.setTypeCode(Arrays.asList("tang-tret,nguyen-can"));
		BuildingDTO building2 = new BuildingDTO();
		building2.setName("BK H2 Building");
		building2.setNumberOfBasement(4L);
		building2.setRentPrice(1000L);
		building2.setTypeCode(Arrays.asList("tang-tret,nguyen-can,noi-that"));
		return Arrays.asList(building1, building2);
	}
	@PostMapping(value = "/api/buildings" )
	public void  createBuilding(@RequestBody BuildingDTO buildingDTO) {
		System.out.print(buildingDTO.getName());
	}
	@DeleteMapping(value = "/api/buildings/{ids}")
	public void deleteBuilding(@PathVariable List<Long> ids
			) {
		System.out.print("Building Delete id = " + ids );
	}
	
	// cach 4
//	@GetMapping(value = "/api/buildings")
//	public Object getBuilding(@RequestParam Map<String, Object> params,
//			@RequestParam(name = "typeCode", required = false) List<String> typeCode) {
//		BuildingDTO building1 = new BuildingDTO();
//		building1.setName("BK H1 Building");
//		building1.setNumberOfBasement(3L);
//		building1.setRentPrice(2000L);
//		building1.setTypeCode(Arrays.asList("tang-tret,nguyen-can"));
//		BuildingDTO building2 = new BuildingDTO();
//		building2.setName("BK H2 Building");
//		building2.setNumberOfBasement(4L);
//		building2.setRentPrice(1000L);
//		building2.setTypeCode(Arrays.asList("tang-tret,nguyen-can,noi-that"));
//		return Arrays.asList(building1, building2);
//	}
//	@PostMapping(value = "/api/buildings" )
//	public void  createBuilding(@RequestBody BuildingDTO buildingDTO) {
//		System.out.print(buildingDTO.getName());
//	}
//	@DeleteMapping(value = "/api/buildings/{id}/{name}")
//	public void deleteBuilding(@PathVariable Long id,
//				@PathVariable  (name= "name") String nameBuilding
//			) {
//		System.out.print("Building Delete id = " + id + " " + nameBuilding);
//	}
	// Cach 3
//	@RequestMapping(value = "/api/buildings" , method = RequestMethod.GET)
//	@ResponseBody
//	public Object  getBuilding(@RequestParam Map<String, Object> params  ,							
//							@RequestParam(name="typeCode", required = false) List<String> typeCode
//			) {
//		BuildingDTO building1 = new BuildingDTO();
//		building1.setName("BK H1 Building");
//		building1.setNumberOfBasement(3L);
//		building1.setRentPrice(2000L);
//		building1.setTypeCode(Arrays.asList("tang-tret,nguyen-can"));
//		BuildingDTO building2 = new BuildingDTO();
//		building2.setName("BK H2 Building");
//		building2.setNumberOfBasement(4L);
//		building2.setRentPrice(1000L);
//		building2.setTypeCode(Arrays.asList("tang-tret,nguyen-can,noi-that"));
//		return Arrays.asList(building1, building2);		
//	}
// Cach 2///////////////////////////////////////////////////////////
//	@RequestMapping(value = "/api/buildings" , method = RequestMethod.GET)
//	public void  getBuilding(@RequestParam Map<String, Object> params  ,							
//							@RequestParam(name="typeCode", required = false) List<String> typeCode
//			) {
//		System.out.print(params.get("name"));
//		System.out.print("ok");
//	}
//	@RequestMapping(value = "/api/buildings" , method = RequestMethod.POST)
//	public void  createBuilding(@RequestBody BuildingDTO buildingDTO) {
//		System.out.print(buildingDTO.getName());
//	}
	// Cach 1//////////////////////////////////////////////////////////
//	@RequestMapping(value = "/api/buildings" , method = RequestMethod.GET)
//	public void  getBuilding(@RequestParam(name="name", required = false) String name,
//							@RequestParam(name ="numberOfBasement", required = false ) Long numberOfBasement,
//							@RequestParam(name="typeCode", required = false) List<String> typeCode
//			) {
//		System.out.print(name + " " + numberOfBasement);
//		System.out.print("ok");
//	}
//	@RequestMapping(value = "/api/buildings" , method = RequestMethod.POST)
//	public void  createBuilding2() {
//		System.out.print("ok");
//	}
	// Cach 1//////////////////////////////////////////////////////////
}
