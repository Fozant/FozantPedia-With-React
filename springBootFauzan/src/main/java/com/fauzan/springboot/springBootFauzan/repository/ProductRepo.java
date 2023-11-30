package com.fauzan.springboot.springBootFauzan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fauzan.springboot.springBootFauzan.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
    
}