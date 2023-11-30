package com.fauzan.springboot.springBootFauzan.controller;

import com.fauzan.springboot.springBootFauzan.model.PurchaseHistory;
import com.fauzan.springboot.springBootFauzan.model.User;
import com.fauzan.springboot.springBootFauzan.service.PurchaseHistoryService;
import com.fauzan.springboot.springBootFauzan.service.UserService;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PurchaseHistoryController {

    private final PurchaseHistoryService purchaseHistoryService;
    private final UserService userService;

    public PurchaseHistoryController(PurchaseHistoryService purchaseHistoryService, UserService userService) {
        this.purchaseHistoryService = purchaseHistoryService;
        this.userService = userService;
    }

    @GetMapping("/purchaseHistory")
    public String getPurchaseHistory(Principal principal, Model model) {
        String currentPrincipalName = principal.getName();
        User user = userService.findByEmail(currentPrincipalName);

        if (user != null) {
            List<PurchaseHistory> purchaseHistoryList = purchaseHistoryService.getPurchaseHistoryByUser(user);
            model.addAttribute("purchaseHistory", purchaseHistoryList);
        } else {
            // Handle the case where the user is not found
        }

        return "purchaseHistory";
    }
}
