package com.fauzan.springboot.springBootFauzan.service;

import com.fauzan.springboot.springBootFauzan.model.PurchaseHistory;
import com.fauzan.springboot.springBootFauzan.model.User;
import com.fauzan.springboot.springBootFauzan.repository.PurchaseHistoryRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {

    private final PurchaseHistoryRepo purchaseHistoryRepository;

    @Autowired
    public PurchaseHistoryServiceImpl(PurchaseHistoryRepo purchaseHistoryRepository) {
        this.purchaseHistoryRepository = purchaseHistoryRepository;
    }

    @Override
    public PurchaseHistory addPurchaseHistory(PurchaseHistory purchaseHistory) {
        return purchaseHistoryRepository.save(purchaseHistory);
    }

    @Override
    public PurchaseHistory getPurchaseHistoryById(Long id) {
        return purchaseHistoryRepository.findById(id).orElse(null);
    }
    
    @Override
    public List<PurchaseHistory> getAllPurchaseHistory() {
        return purchaseHistoryRepository.findAll(); 
    }

	@Override
	public List<PurchaseHistory> getPurchaseHistoryByUser(User user) {
		 return purchaseHistoryRepository.findByUser(user);
	}
}
