package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private BuildingDTOConverter buildingDTOConverter;
	
	@Autowired
	private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
	
	
	public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode) {
//		BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(params, typeCode);
//		List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder);
//		List<BuildingDTO> buildingDTOs = new ArrayList<BuildingDTO>();
//		for(BuildingEntity item : buildingEntities) {
//			BuildingDTO buildingDTO = buildingDTOConverter.toBuildingDTO(item);
//			buildingDTOs.add(buildingDTO);
//		}
//		return buildingDTOs;
		return null;
	}

	@Override
	public void create(BuildingDTO buildingDTO) {
		// TODO Auto-generated method stub
		
	}
}
