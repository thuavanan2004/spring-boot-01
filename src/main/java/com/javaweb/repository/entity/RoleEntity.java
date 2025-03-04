package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class RoleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "code", nullable = false, unique = true)
	private String code;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	        name = "user_role", 
	        joinColumns = { @JoinColumn(name = "roleid", nullable = false) }, 
	        inverseJoinColumns = { @JoinColumn(name = "userid", nullable = false) }
	    )
	private List<UserEntity> users = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	
	

}
