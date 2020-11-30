package com.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.model.Role;
import com.springboot.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public List<Role> findAll() {
		List<Role> roles = new ArrayList<>();
		roleRepository.findAll().forEach(roles::add);
		return roles;
	}

	public void save(Role role) {
		roleRepository.save(role);
	}

	public Role get(String id) {
		return roleRepository.findById(id).get();
	}

	public void delete(String id) {
		roleRepository.deleteById(id);
	}
	
	public List<String> getRoleNameList() {
		return roleRepository.getRoleNameList();
	}
}
