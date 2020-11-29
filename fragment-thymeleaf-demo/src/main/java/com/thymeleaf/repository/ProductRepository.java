package com.thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thymeleaf.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
