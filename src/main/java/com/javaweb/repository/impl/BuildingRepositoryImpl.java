package com.javaweb.repository.impl;

import java.beans.JavaBean;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionJDBCUtil;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	public static void join(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
		String staffId = buildingSearchBuilder.getStaffId();
		if(StringUtil.checkString(staffId)) {
			sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
		}
		
		List<String> typeCode = buildingSearchBuilder.getTypeCodeList();
		if(typeCode != null && typeCode.size() != 0) {
			sql.append(" INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
			sql.append(" INNER JOIN renttype ON b.id = buildingrenttype.renttypeid ");
		}
	}
	public static void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
//		for(Map.Entry<String, Object> it : params.entrySet()) {
//			if(!it.getKey().equals("staffId") && !it.getKey().equals("typeCode") && !it.getKey().startsWith("area") && !it.getKey().startsWith("rentPrice")) {
//				String value = it.getValue().toString();
//				if(StringUtil.checkString(value) && NumberUtil.isNumber(value)) {
//					where.append(" AND b." + it.getKey() + " = " + value);
//				}else {
//					where.append(" AND b." + it.getKey() + " LIKE '% " + value + " %' ");
//				}
//			}
//		}	
		
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for(Field item : fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if(!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area") && !fieldName.startsWith("rentPrice")) {
					String value = fieldName.toString();
					if(StringUtil.checkString(value) && NumberUtil.isNumber(value)) {
						where.append(" AND b." + fieldName + " = " + value);
					}else {
						where.append(" AND b." + fieldName + " LIKE '% " + value + " %' ");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		String staffId = buildingSearchBuilder.getStaffId();
		if(StringUtil.checkString(staffId)) {
			where.append(" AND assignmentbuilding.staffid = " + staffId);
		}
		
		String rentAreaTo = (String)params.get("areaTo");
		String rentAreaFrom = (String)params.get("areaFrom");
		if(StringUtil.checkString(rentAreaFrom) || StringUtil.checkString(rentAreaTo)) {
			where.append(" AND EXISTS ( SELECT * FROM rentarea WHERE b.id = rentarea.buildingid ");
			
			if(StringUtil.checkString(rentAreaFrom)) {
				where.append(" AND rentarea.value >= " + rentAreaFrom);
			}
			if(StringUtil.checkString(rentAreaTo)) {
				where.append(" AND rentarea.value <= " + rentAreaTo);
			}
			where.append(" ) ");
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
//	    Java 7
//		if(typeCode != null && typeCode.size() != 0) {
//			List<String> codeList = new ArrayList<String>();
//			for(String item : typeCode) {
//				codeList.add("'" + item + "'");
//			}
//			where.append(" AND renttype.code IN(" + String.join(",", codeList) + ")");
//		}
		
//		Java 8
		if(typeCode != null && typeCode.size() != 0) {
			where.append(" AND ( ");
			String sql = typeCode.stream().map(it -> "renttype.code LIKE '%" + it +  "%' ").collect(Collectors.joining(" OR "));
			where.append(sql + " ) ");
		}
		
	}
	
	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.district, b.numberofbasement, b.floorarea, b.rentprice, b.managername, b.managerphone, b.servicefee, b.brokeragefee FROM building b");
		join(buildingSearchBuilder, sql);
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		queryNormal(buildingSearchBuilder, where);
		querySpecial(buildingSearchBuilder, where);
		where.append(" GROUP BY b.id;");
		sql.append(where);
		System.out.print(sql);
		List<BuildingEntity> resultBuildingEntities = new ArrayList<>();
		try( Connection connection = ConnectionJDBCUtil.getConnection();
			 Statement stmt = connection.createStatement();
			 ResultSet rs = stmt.executeQuery(sql.toString());){
			while(rs.next()) {
				BuildingEntity buildingEntity = new BuildingEntity();
				buildingEntity.setId(rs.getLong("id"));
				buildingEntity.setName(rs.getString("name"));
				buildingEntity.setDistrict(rs.getString("district"));
				buildingEntity.setNumberOfBasement(rs.getInt("numberofbasement"));
				buildingEntity.setFloorArea(rs.getLong("floorarea"));
				buildingEntity.setRentPrice(rs.getLong("rentprice"));
				buildingEntity.setManagerName(rs.getString("managername"));
				buildingEntity.setManagerPhone(rs.getString("managerphone"));
				buildingEntity.setServiceFee(rs.getString("servicefee"));
				buildingEntity.setBrokerageFee(rs.getString("brokeragefee"));
				
				resultBuildingEntities.add(buildingEntity);
			}
			
		} catch( SQLException e) {
			e.printStackTrace();
		}
		return resultBuildingEntities;
	}

}
