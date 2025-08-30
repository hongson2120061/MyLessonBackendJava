package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.util.ConnectionDriverUtils;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {

	@Override
	public List<RentAreaEntity> findById(Long buildingId) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("");
		sql.append("SELECT *FROM rentarea r WHERE r.buildingid = " + buildingId);
		List<RentAreaEntity> results = new ArrayList<RentAreaEntity>();
		try (Connection con = ConnectionDriverUtils.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql.toString());) {
			while (rs.next()) {
				RentAreaEntity rentAreaEntity = new RentAreaEntity();
				rentAreaEntity.setId(rs.getLong("id"));
				;
				rentAreaEntity.setValue(rs.getLong("value"));
				rentAreaEntity.setBuildingId(rs.getLong("buildingid"));
				results.add(rentAreaEntity);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected database failed...");
		}
		return results;
	}

}
