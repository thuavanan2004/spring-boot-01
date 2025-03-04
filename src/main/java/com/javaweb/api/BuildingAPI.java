package com.javaweb.api;

import org.springframework.web.bind.annotation.RestController;

import com.javaweb.customException.FieldRequiredException;
import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
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
		BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, id);
		entityManager.remove(buildingEntity);
	}
	
	@PostMapping("/api/building/create")
	public void createBuilding(@RequestBody BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.setName(buildingDTO.getName());
		buildingEntity.setDistrict(buildingDTO.getDistrict());
		entityManager.persist(buildingEntity);
	}
	@PutMapping("/api/building/update")
	public void updateBuilding(@RequestBody BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.setName(buildingDTO.getName());
		buildingEntity.setDistrict(buildingDTO.getDistrict());
		entityManager.merge(buildingEntity);
	}
	
	@GetMapping("/api/building/{ids}")
	public void deleteMany(@PathVariable Long[] Ids) {
		buildingRepository.deleteByIdIn(Ids);
	}
	
	@GetMapping("/api/building")
	public void findByName(@RequestBody String s) {
		buildingRepository.findByNameContaining(s);
	}

	@GetMapping("/api/building")
	public void findByNameAndStreet(@RequestBody String name, String street) {
		buildingRepository.findByNameContainingAndStreet(name, street);
	}
}
