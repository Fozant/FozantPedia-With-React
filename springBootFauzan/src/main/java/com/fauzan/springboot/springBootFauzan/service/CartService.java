package com.fauzan.springboot.springBootFauzan.service;

import java.util.List;

import com.fauzan.springboot.springBootFauzan.model.User;
import com.fauzan.springboot.springBootFauzan.model.UserCart;

public interface CartService {


    void addProductToCart(long productId, long userId);
    User getUserById(long userId);
    List<UserCart> getProductsFromUserCartByUserId(long userId);
    void removeProductFromCart(long userCartId);
    UserCart getUserCartById(long userCartId);
    
    

}
