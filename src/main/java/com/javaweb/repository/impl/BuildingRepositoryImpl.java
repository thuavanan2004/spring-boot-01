package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	static final String DB_URL = "jdbc:mysql://localhost:3306/estateadvance";
	static final String USER = "root";
	static final String PASS = "";
	public static void join(Map<String, Object> params, List<String> typeCode, StringBuilder sql) {
		String staffId = (String)params.get("staffId");
		if(StringUtil.checkString(staffId)) {
			sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
		}
		if(typeCode != null && typeCode.size() != 0) {
			sql.append(" INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
			sql.append(" INNER JOIN renttype ON b.id = buildingrenttype.renttypeid ");
		}
		String rentAreaTo = (String)params.get("areaTo");
		String rentAreaFrom = (String)params.get("areaFrom");
		if(StringUtil.checkString(rentAreaFrom) && StringUtil.checkString(rentAreaTo)) {
			sql.append(" INNER JOIN rentarea ON b.id = rentarea.buildingid ");
		}
	}
	public static void queryNormal(Map<String, Object> params, StringBuilder where) {
		for(Map.Entry<String, Object> it : params.entrySet()) {
			if(!it.getKey().equals("staffId") && !it.getKey().equals("typeCode") && !it.getKey().startsWith("area") && !it.getKey().startsWith("rentPrice")) {
				String value = it.getValue().toString();
				if(StringUtil.checkString(value) && NumberUtil.isNumber(value)) {
					where.append(" AND b." + it.getKey() + " = " + value);
				}else {
					where.append(" AND b." + it.getKey() + " LIKE '% " + value + " %' ");
				}
			}
		}
	}
	public static void querySpecial(Map<String, Object> params, List<String> typeCode, StringBuilder where) {
		String staffId = (String)params.get("staffId");
		if(StringUtil.checkString(staffId)) {
			where.append(" AND assignmentbuilding.staffid = " + staffId);
		}
		
		String rentAreaTo = (String)params.get("areaTo");
		String rentAreaFrom = (String)params.get("areaFrom");
		if(StringUtil.checkString(rentAreaFrom) || StringUtil.checkString(rentAreaTo)) {
			if(StringUtil.checkString(rentAreaFrom)) {
				where.append(" AND rentarea.value >= " + rentAreaFrom);
			}
			if(StringUtil.checkString(rentAreaTo)) {
				where.append(" AND rentarea.value <= " + rentAreaTo);
			}
		}
		
		String rentPriceTo = (String)params.get("rentPriceTo");
		String rentPriceFrom = (String)params.get("rentPriceFrom");
		if(StringUtil.checkString(rentPriceFrom) || StringUtil.checkString(rentPriceTo)) {
			if(StringUtil.checkString(rentPriceFrom)) {
				where.append(" AND b.rentprice >= " + rentPriceFrom);
			}
			if(StringUtil.checkString(rentPriceTo)) {
				where.append(" AND b.rentprice <= " + rentPriceTo);
			}
		}
	
		if(typeCode != null && typeCode.size() != 0) {
			List<String> codeList = new ArrayList<String>();
			for(String item : typeCode) {
				codeList.add("'" + item + "'");
			}
			where.append(" AND renttype.code IN(" + String.join(",", codeList) + ")");
		}
	}
	
	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCode) {
		StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.district, b.numberofbasement, b.floorarea, b.rentprice, b.managername, b.managerphone, b.servicefee, b.brokeragefee FROM building b");
		join(params, typeCode, sql);
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		queryNormal(params, where);
		querySpecial(params, typeCode, where);
		where.append(" GROUP BY b.id;");
		sql.append(where);
		System.out.print(sql);
		List<BuildingEntity> resultBuildingEntities = new ArrayList<>();
		try( Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
			 Statement stmt = connection.createStatement();
			 ResultSet rs = stmt.executeQuery(sql.toString());){
			while(rs.next()) {
				BuildingEntity buildingEntity = new BuildingEntity();
				buildingEntity.setName(rs.getString("name"));
				buildingEntity.setStreet(rs.getString("street"));
				buildingEntity.setWard(rs.getString("ward"));
//				buildingEntity.setNumberOfBasement(rs.getInt("numberOfBasement"));
				
				resultBuildingEntities.add(buildingEntity);
			}
			
		} catch( SQLException e) {
			e.printStackTrace();
		}
		return resultBuildingEntities;
	}

}
