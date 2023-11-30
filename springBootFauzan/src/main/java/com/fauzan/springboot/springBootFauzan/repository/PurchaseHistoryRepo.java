package com.fauzan.springboot.springBootFauzan.repository;

import com.fauzan.springboot.springBootFauzan.model.PurchaseHistory;
import com.fauzan.springboot.springBootFauzan.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseHistoryRepo extends JpaRepository<PurchaseHistory, Long> {
	List<PurchaseHistory> findByUser(User user);
}
