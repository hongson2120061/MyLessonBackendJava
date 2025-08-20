package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "123456";

	@Override
	public List<BuildingEntity> findAll(String nameBuilding, Long numberOfBasement) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("SELECT b.* FROM building b WHERE 1= 1 ");
		if (nameBuilding != null && !nameBuilding.equals("")) {
			sql.append(" AND b.name LIKE '%" + nameBuilding + "%'");
		}
		if (numberOfBasement != null) {
			sql.append("  AND b.numberofbasement = " + numberOfBasement);
		}
		List<BuildingEntity> results = new ArrayList<BuildingEntity>();
		try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql.toString());) {
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getLong("id"));
				building.setName(rs.getString("name"));
				building.setDistrictId(rs.getLong("districtid"));
				building.setNumberOfBasement(rs.getLong("numberOfBasement"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setRentPrice(rs.getLong("rentprice"));
				results.add(building);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected database failed...");
		}

		return results;
	}

}
