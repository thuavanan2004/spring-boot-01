package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO buildingDTO = modelMapper.map(item, BuildingDTO.class);
//		BuildingDTO buildingDTO = new BuildingDTO();
//		buildingDTO.setId(item.getId());
//		buildingDTO.setName(item.getName());
//		buildingDTO.setDistrict(item.getDistrict());
//		buildingDTO.setNumberOfBasement(item.getNumberOfBasement());
//		buildingDTO.setFloorArea(item.getFloorArea());
//		buildingDTO.setRentPrice(item.getRentPrice());
//		buildingDTO.setManagerName(item.getManagerName());
//		buildingDTO.setManagerPhone(item.getManagerPhone());
//		buildingDTO.setServiceFee(item.getServiceFee());
//		buildingDTO.setBrokerageFee(item.getBrokerageFee());
		List<RentAreaEntity> rentAreas = rentAreaRepository.getValueByBuildingId(item.getId());
		String rentAreaResult = rentAreas.stream().map(it -> it.getValue()).collect(Collectors.joining(","));	
		buildingDTO.setRentArea(rentAreaResult);
		
		return buildingDTO;
	}
}
