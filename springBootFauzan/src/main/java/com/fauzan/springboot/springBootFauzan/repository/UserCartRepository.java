package com.fauzan.springboot.springBootFauzan.repository;

import com.fauzan.springboot.springBootFauzan.model.UserCart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCartRepository extends JpaRepository<UserCart, Long> {
	List<UserCart> findByUserId(Long userId);
}
