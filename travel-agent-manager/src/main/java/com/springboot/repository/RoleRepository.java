package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.model.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
	
	@Query("SELECT u.name FROM Role u")
	public List<String> getRoleNameList();
}
