package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.model.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
