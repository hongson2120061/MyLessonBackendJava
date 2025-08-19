package com.javaweb.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.myexception.ValidateDataBuildingException;

@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "123456";

	@GetMapping
	public Object getBuilding(@RequestParam (name ="name", required = false) String nameBuilding,
			@RequestParam(name = "numberOfBasement", required = false) Long numberOfBasement,
			@RequestParam(name = "typeCode", required = false) List<String> typeCode) {
		StringBuilder sql = new StringBuilder("SELECT b.* FROM building b WHERE 1= 1 ");
		if(nameBuilding != null && !nameBuilding.equals("")) {
			sql.append(" AND b.name LIKE '%" + nameBuilding + "%'");
		}
		if(numberOfBasement != null ) {
			sql.append("  AND b.numberofbasement = "  + numberOfBasement);
		}
		List<BuildingDTO> results = new ArrayList<BuildingDTO>();
		try (
				Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql.toString());) {
			//cac thu vien tren import tu java.sql
			while (rs.next()) {
				// moi lan lap la lay gia tri mot dong cua database
				BuildingDTO building = new BuildingDTO();
				building.setName(rs.getString("name"));
				building.setNumberOfBasement(rs.getLong("numberOfBasement"));
				building.setRentPrice(rs.getLong("rentprice"));
				results.add(building);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected database failed...");
		}

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
