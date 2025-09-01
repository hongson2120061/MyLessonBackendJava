package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.util.ConnectionDriverUtils;
import com.javaweb.util.StringUtils;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

	private void buildJoin(BuildingSearchBuilder buildingSearchBuilder, StringBuilder join) {
		
		if (buildingSearchBuilder.getStaffId()!= null) {
			join.append(" JOIN assignmentbuilding asb on asb.buildingid = b.id ");
		}
		if (buildingSearchBuilder.getTypeCode() != null && !buildingSearchBuilder.getTypeCode().isEmpty()) {
			join.append(" join buildingrenttype brt on brt.buildingid = b.id  ");
			join.append(" join renttype rt  on rt.id =  brt.renttypeid ");
		}
		if (buildingSearchBuilder.getRentAreaFrom()!= null  || buildingSearchBuilder.getRentAreaTo() != null ) {
			join.append(" join rentarea on rentarea.buildingid = b.id ");
		}
		
		
//		version 1
//		String staffId = (String) params.get("staffId");
//		if (StringUtils.isNotBlank(staffId)) {
//			join.append(" JOIN assignmentbuilding asb on asb.buildingid = b.id ");
//		}
//		if (typeCode != null && !typeCode.isEmpty()) {
//			join.append(" join buildingrenttype brt on brt.buildingid = b.id  ");
//			join.append(" join renttype rt  on rt.id =  brt.renttypeid ");
//		}
//		String rentAreaFrom = (String) params.get("rentAreaFrom");
//		String rentAreaTo = (String) params.get("rentAreaTo");
//		if (StringUtils.isNotBlank(rentAreaFrom) || StringUtils.isNotBlank(rentAreaTo)) {
//			join.append(" join rentarea on rentarea.buildingid = b.id ");
//		}
	}

	private void buildCondition(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field item : fields) {
				item.setAccessible(true);
				String key = item.getName();
				if (!key.equals("staffId") && !key.equals("typeCode") && !key.startsWith("rentArea")
						&& !key.startsWith("rentPrice")) {
					Object value = item.get(buildingSearchBuilder);
					if (value != null) {
						if (StringUtils.isNumber(value.toString())) {
							where.append(" AND b. " + key + " = " + value.toString());
						} else {
							where.append(" AND b. " + key + " Like '% " + value.toString() + "%' ");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		for (Map.Entry<String, Object> item : params.entrySet()) {
//			String key = item.getKey();
//			if (!key.equals("staffId") && !key.equals("typeCode") && !key.startsWith("rentArea")
//					&& !key.startsWith("rentPrice")) {
//				Object value = item.getValue();
//				if (StringUtils.isNotBlank(value.toString())) {
//					if (StringUtils.isNumber(value.toString())) {
//						where.append(" AND b. " + key + " = " + value.toString());
//					} else {
//						where.append(" AND b. " + key + " Like '% " + value.toString() + "%' ");
//					}
//				}
//			}			
//		}
		// Xu ly truong hop special
		Long staffId = buildingSearchBuilder.getStaffId();
		if (staffId != null) {
			where.append(" AND asb.staffid = " + staffId);
		}
		Long rentAreaFrom = buildingSearchBuilder.getRentAreaFrom();
		Long rentAreaTo = buildingSearchBuilder.getRentAreaTo();
		if (rentAreaFrom != null) {
			where.append(" AND rentarea.value >= " + rentAreaFrom);
		}
		if (rentAreaTo != null) {
			where.append(" AND rentarea.value <= " + rentAreaFrom);
		}

		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		if (typeCode != null && !typeCode.isEmpty()) {
			where.append(" AND rt.code IN ("
					+ typeCode.stream().map(i -> "'" + i + "'").collect(Collectors.joining(",")) + ")");
		}

		Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
		Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
		if (rentPriceFrom != null) {
			where.append(" AND b.rentprice >= " + rentPriceFrom);
		}
		if (rentPriceTo != null) {
			where.append(" AND b.rentprice <= " + rentPriceTo);
		}

	}

	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("SELECT b.* FROM building b ");
		StringBuilder where = new StringBuilder("  WHERE 1=1 ");
		buildJoin(buildingSearchBuilder, sql);
		buildCondition(buildingSearchBuilder, where);
		sql.append(where).append(" GROUP BY b.id ");
		List<BuildingEntity> results = new ArrayList<BuildingEntity>();
		try (Connection con = ConnectionDriverUtils.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql.toString());) {
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getLong("id"));
				building.setName(rs.getString("name"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setDistrictId(rs.getLong("districtid"));
				building.setStructure(rs.getString("structure"));
				building.setNumberOfBasement(rs.getLong("numberofbasement"));
				building.setFloorArea(rs.getLong("floorarea"));
				building.setDirection(rs.getString("direction"));
				building.setLevel(rs.getString("level"));
				building.setRentPrice(rs.getLong("rentprice"));
				building.setRentPriceDescription(rs.getString("rentpricedescription"));
				building.setServiceFee(rs.getString("ServiceFee"));
				building.setCarFee(rs.getString("carfee"));
				building.setMotorbikeFee(rs.getString("motorbikefee"));
				building.setOvertimeFee(rs.getString("overtimefee"));
				building.setWaterFee(rs.getString("waterfee"));
				building.setElectricityFee(rs.getString("electricityfee"));
				building.setDeposit(rs.getString("deposit"));
				building.setPayment(rs.getString("payment"));
				building.setRentTime(rs.getString("renttime"));
				building.setDecorationTime(rs.getString("decorationtime"));
				building.setBrokerageFee(rs.getLong("brokeragefee"));
				building.setNote(rs.getString("note"));
				building.setLinkOfBuilding(rs.getString("linkofbuilding"));
				building.setMap(rs.getString("map"));
				building.setImage(rs.getString("image"));
				building.setCreatedDate(rs.getTimestamp("createddate") != null
						? rs.getTimestamp("createddate").toLocalDateTime().toLocalDate()
						: null);
				building.setModifiedDate(rs.getTimestamp("modifieddate") != null
						? rs.getTimestamp("modifieddate").toLocalDateTime().toLocalDate()
						: null);
				building.setCreatedBy(rs.getString("createdby"));
				building.setModifiedBy(rs.getString("modifiedby"));
				building.setManagerName(rs.getString("managername"));
				building.setManagerPhoneNumber(rs.getString("managerphonenumber"));
				results.add(building);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected database failed...");
		}

		return results;
	}

}
