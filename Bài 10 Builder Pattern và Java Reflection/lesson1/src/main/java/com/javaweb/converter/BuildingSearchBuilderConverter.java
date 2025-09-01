package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.util.MapUtils;

// dung de giam so luong tham so trong param cua ham
@Component
public class BuildingSearchBuilderConverter {
	
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params,  List<String> typeCode ) {
		BuildingSearchBuilder buildingSearchBuilder =    new BuildingSearchBuilder.Builder().name(MapUtils.getObject(params, "name", String.class))
																							.floorArea(MapUtils.getObject(params, "floorArea", Long.class))
																							.districtId(MapUtils.getObject(params, "districtId", Long.class))
																							.ward(MapUtils.getObject(params, "ward", String.class))
																							.street(MapUtils.getObject(params, "street", String.class))
																							.numberOfBasement(MapUtils.getObject(params, "numberOfBasement", Long.class))
																							.direction(MapUtils.getObject(params, "direction", String.class))
																							.level(MapUtils.getObject(params, "level", String.class))
																							.rentAreaFrom(MapUtils.getObject(params, "rentAreaFrom", Long.class))
																							.rentAreaTo(MapUtils.getObject(params, "rentAreaTo", Long.class))
																							.rentPriceFrom(MapUtils.getObject(params, "rentPriceFrom", Long.class))
																							.rentPriceTo(MapUtils.getObject(params, "rentPriceTo", Long.class))
																							.managerName(MapUtils.getObject(params, "managerName", String.class))
																							.managerPhoneNumber(MapUtils.getObject(params, "managerPhoneNumber", String.class))
																							.staffId(MapUtils.getObject(params, "staffId", Long.class))
																							.typeCode(typeCode)
																							.build();
		
		return buildingSearchBuilder ;
	}
	
}
