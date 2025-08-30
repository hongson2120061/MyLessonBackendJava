package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.util.ConnectionDriverUtils;
import com.javaweb.util.StringUtils;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

	private void buildJoin(Map<String, Object> params, List<String> typeCode, StringBuilder join) {
		String staffId = (String) params.get("staffId");
		if (StringUtils.isNotBlank(staffId)) {
			join.append(" JOIN assignmentbuilding asb on asb.buildingid = b.id ");
		}
		if (typeCode != null && !typeCode.isEmpty()) {
			join.append(" join buildingrenttype brt on brt.buildingid = b.id  ");
			join.append(" join renttype rt  on rt.id =  brt.renttypeid ");
		}
		String rentAreaFrom = (String) params.get("rentAreaFrom");
		String rentAreaTo = (String) params.get("rentAreaTo");
		if (StringUtils.isNotBlank(rentAreaFrom) || StringUtils.isNotBlank(rentAreaTo)) {
			join.append(" join rentarea on rentarea.buildingid = b.id ");
		}

//	 version 1
//	//Do mot ham khong lam qua nhieu viec nen ta tach ra 1 ham de xu ly
//	// Trong java ham nao chi xu ly trong mot class thi nen de la private
//	private void buildJoin(Map<String, Object> params, List<String> typeCode, StringBuilder join) {
//		// Thuong doi qua string cho de xu ly
//		String  staffId = (String) params.get("staffId");
//		if(staffId != null && staffId.isEmpty()) {
//			join.append(" JOIN assignmentbuilding asb on asb.buildingid = b.id ");
//		}
//		if(typeCode != null && !typeCode.isEmpty()) {
//			join.append(" join buildingrenttype brt on brt.buildingid = b.id  ");
//			join.append(" join renttype rt  on rt.id =  brt.renttypeid ");
//		}
//		String rentAreaFrom = (String) params.get("rentAreaFrom");
//		String rentAreaTo = (String) params.get("rentAreaTo");
//		if((rentAreaFrom != null && !rentAreaFrom.isEmpty()) || (rentAreaTo != null && !rentAreaTo.isEmpty())) {
//			join.append(" join rentarea on rentarea.buildingid = b.id ");
//		}
	}

	private void buildCondition(Map<String, Object> params, List<String> typeCode, StringBuilder where) {
		// Normal: like(data la String), = (data la so)
		// Special: >=, <= (rentType.code like '%tang-tret%' OR rentType.code like
		// '%noi-that%'), Field join table
		// xu ly truong hop normal
		for (Map.Entry<String, Object> item : params.entrySet()) {
			String key = item.getKey();
			if (!key.equals("staffId") && !key.equals("typeCode") && !key.startsWith("rentArea")
					&& !key.startsWith("rentPrice")) {
				Object value = item.getValue();
				if (StringUtils.isNotBlank(value.toString())) {
					if (StringUtils.isNumber(value.toString())) {
						where.append(" AND b. " + key + " = " + value.toString());
					} else {
						where.append(" AND b. " + key + " Like '% " + value.toString() + "%' ");
					}
				}
			}
			// Xu ly truong hop special
			String staffId = (String) params.get("staffId");
			if (StringUtils.isNotBlank(staffId)) {
				where.append(" AND asb.staffid = " + staffId);
			}
			String rentAreaFrom = (String) params.get("rentAreaFrom");
			String rentAreaTo = (String) params.get("rentAreaTo");
			if (StringUtils.isNotBlank(rentAreaFrom)) {
				where.append(" AND rentarea.value >= " + rentAreaFrom);
			}
			if (StringUtils.isNotBlank(rentAreaTo)) {
				where.append(" AND rentarea.value <= " + rentAreaFrom);
			}
			if (typeCode != null && !typeCode.isEmpty()) {
				where.append(" AND rt.code IN ("
						+ typeCode.stream().map(i -> "'" + i + "'").collect(Collectors.joining(",")) + ")");
			}

			String rentPriceFrom = (String) params.get("rentPriceFrom");
			String rentPriceTo = (String) params.get("rentPriceTo");
			if (StringUtils.isNotBlank(rentPriceFrom)) {
				where.append(" AND b.rentprice >= " + rentPriceFrom);
			}
			if (StringUtils.isNotBlank(rentPriceTo)) {
				where.append(" AND b.rentprice <= " + rentPriceTo);
			}

//version 1		
//		// xu ly truong hop normal
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
//			// Xu ly truong hop special
//			String staffId = (String) params.get("staffId");
//			if (StringUtils.isNotBlank(staffId)) {
//				where.append(" AND asb.staffid = " + staffId);
//			}
//			String rentAreaFrom = (String) params.get("rentAreaFrom");
//			String rentAreaTo = (String) params.get("rentAreaTo");
//			if (StringUtils.isNotBlank(rentAreaFrom)) {
//				where.append(" AND rentarea.value >= " + rentAreaFrom);
//			}
//			if (StringUtils.isNotBlank(rentAreaTo)) {
//				where.append(" AND rentarea.value <= " + rentAreaFrom);
//			}
//			if (typeCode != null && !typeCode.isEmpty()) {
//				where.append(" AND rt.code IN (");
//				for (int i = 0; i < typeCode.size(); i++) {
//					where.append("'" + typeCode.get(i) + "'");
//					if (i < typeCode.size() - 1) {
//						where.append(",");
//					}
//				}
//				where.append(")");
//			}
//
//			String rentPriceFrom = (String) params.get("rentPriceFrom");
//			String rentPriceTo = (String) params.get("rentPriceTo");
//			if (StringUtils.isNotBlank(rentPriceFrom)) {
//				where.append(" AND b.rentprice >= " + rentPriceFrom);
//			}
//			if (StringUtils.isNotBlank(rentPriceTo)) {
//				where.append(" AND b.rentprice <= " + rentPriceTo);
//			}
		}
	}

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCode) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("SELECT b.* FROM building b ");
		StringBuilder where = new StringBuilder("  WHERE 1=1 ");
		buildJoin(params, typeCode, sql);
		buildCondition(params, typeCode, where);
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
