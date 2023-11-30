package com.fauzan.springboot.springBootFauzan.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fauzan.springboot.springBootFauzan.model.PurchaseHistory;
import com.fauzan.springboot.springBootFauzan.model.User;
import com.fauzan.springboot.springBootFauzan.model.UserCart;
import com.fauzan.springboot.springBootFauzan.service.CartService;
import com.fauzan.springboot.springBootFauzan.service.ProductService;
import com.fauzan.springboot.springBootFauzan.service.PurchaseHistoryService;
import com.fauzan.springboot.springBootFauzan.service.UserService;
@Controller
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final PurchaseHistoryService purchaseHistoryService;

    public CartController(ProductService productService, CartService cartService, UserService userService,
            PurchaseHistoryService purchaseHistoryService) {
        this.cartService = cartService;
        this.userService = userService;
        this.purchaseHistoryService = purchaseHistoryService;
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("productId") long productId, Authentication authentication) {
        String currentPrincipalName = authentication.getName();
        User user = userService.findByEmail(currentPrincipalName);

        if (user != null) {
            long userId = user.getId();
            cartService.addProductToCart(productId, userId);
        }

        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String viewUserCart(Principal principal, Model model) {
        String currentPrincipalName = principal.getName();
        User user = userService.findByEmail(currentPrincipalName);

        if (user != null) {
            List<UserCart> userCartProducts = cartService.getProductsFromUserCartByUserId(user.getId());
            model.addAttribute("userCartProducts", userCartProducts);
        }

        return "cart";
    }

    @PostMapping("/cart/checkout")
    public String checkoutCart(@RequestParam("userCartId") long userCartId, Principal principal) {
        UserCart userCartItem = cartService.getUserCartById(userCartId);

        if (userCartItem != null) {
            PurchaseHistory purchaseHistory = new PurchaseHistory();
            purchaseHistory.setUser(userCartItem.getUser());
            purchaseHistory.setProductId(userCartItem.getProductId());
            purchaseHistory.setProductName(userCartItem.getProductName());
            purchaseHistory.setProductDescription(userCartItem.getProductDescription());
            purchaseHistory.setProductPrice(userCartItem.getProductPrice());
            purchaseHistory.setPurchaseDate(new Date());
            purchaseHistory.setImageUrl(userCartItem.getImageUrl());
            purchaseHistoryService.addPurchaseHistory(purchaseHistory);
            cartService.removeProductFromCart(userCartId);
        }

        return "redirect:/purchaseHistory";
    }
}
