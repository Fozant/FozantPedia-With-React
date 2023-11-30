package com.fauzan.springboot.springBootFauzan.service;

import java.util.List;

import com.fauzan.springboot.springBootFauzan.model.PurchaseHistory;
import com.fauzan.springboot.springBootFauzan.model.User;

public interface PurchaseHistoryService {

    PurchaseHistory addPurchaseHistory(PurchaseHistory purchaseHistory);
    PurchaseHistory getPurchaseHistoryById(Long id);
    List<PurchaseHistory> getAllPurchaseHistory();
    List<PurchaseHistory> getPurchaseHistoryByUser(User user);
}
