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
import com.javaweb.utils.ConnectionJDBCUtil;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {

	@Override
	public List<RentAreaEntity> getValueByBuildingId(Long id) {
		List<RentAreaEntity> rentAreas = new ArrayList<>();
		String sql = "SELECT * FROM rentarea WHERE buildingid = " + id ;
		try( Connection connection = ConnectionJDBCUtil.getConnection();
			 Statement sttm = connection.createStatement();
			 ResultSet resultSet = sttm.executeQuery(sql);) {
			while(resultSet.next()) {
				RentAreaEntity rentArea = new RentAreaEntity();
				rentArea.setId(resultSet.getLong("id"));
				rentArea.setValue(resultSet.getString("value"));
				
				rentAreas.add(rentArea);
			}
		} catch( SQLException e) {
			e.printStackTrace();
		}
		return rentAreas;
	}

}
