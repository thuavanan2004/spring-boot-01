package com.javaweb.api;

import org.springframework.web.bind.annotation.RestController;

import com.javaweb.customException.FieldRequiredException;
import com.javaweb.model.BuildingDTO;
import com.javaweb.service.BuildingService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;
	
	@GetMapping("/api/building")
	public Object getBuilding(@RequestParam Map<String, Object> params, @RequestParam(name = "typeCode", required = false) List<String> typeCode) {
		List<BuildingDTO> result = buildingService.findAll(params, typeCode); 
		return result;
	}
	
	public void validate(BuildingDTO buildingDTO) throws FieldRequiredException {
		if(buildingDTO.getName().equals("") || buildingDTO.getNumberOfBasement() == null) {
			throw new FieldRequiredException("Vui long khong de ten bang rong hoac so luong bang 0!");
		}
	}
	
	@DeleteMapping("/api/building/{id}")
	public void deleteBuilding(@PathVariable Integer id) {
		System.out.println("Da xoa mot building voi id la " + id );
	}

}
