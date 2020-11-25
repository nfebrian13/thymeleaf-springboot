package com.login.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.demo.model.Users;

public interface UsersRepository extends JpaRepository<Users, String> {
	Optional<Users> findByUsername(String username);
}
