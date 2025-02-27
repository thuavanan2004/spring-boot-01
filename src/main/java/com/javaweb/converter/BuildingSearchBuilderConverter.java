package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;

@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typeCode) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
																				.setId(MapUtil.getObject(params, "id", Long.class))
																				.setName(MapUtil.getObject(params, "name", String.class))
																				.setDistrict(MapUtil.getObject(params, "district", String.class))
																				.setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", Integer.class))
																				.setFloorArea(MapUtil.getObject(params, "floorArea", Long.class))
																				.setRentAreaTo(MapUtil.getObject(params, "areaTo", String.class))
																				.setRentAreaFrom(MapUtil.getObject(params, "areaFrom", String.class))
																				.setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", String.class))
																				.setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", String.class))
																				.setManagerName(MapUtil.getObject(params, "managerName", String.class))
																				.setManagerPhone(MapUtil.getObject(params, "managerPhone", String.class))
																				.setServiceFee(MapUtil.getObject(params, "serviceFee", String.class))
																				.setBrokerageFee(MapUtil.getObject(params, "brokerageFee", String.class))
																				.setStaffId(MapUtil.getObject(params, "staffId", String.class))
																				.setTypeCodeList(typeCode)
																				.build();
		
		return buildingSearchBuilder;
	}
}
