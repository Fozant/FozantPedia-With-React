package com.fauzan.springboot.springBootFauzan.service;

import com.fauzan.springboot.springBootFauzan.model.Product;
import com.fauzan.springboot.springBootFauzan.model.User;
import com.fauzan.springboot.springBootFauzan.model.UserCart;
import com.fauzan.springboot.springBootFauzan.repository.UserCartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImp implements CartService {

    private final UserCartRepository userCartRepository;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public CartServiceImp(UserCartRepository userCartRepository, ProductService productService, UserService userService) {
        this.userCartRepository = userCartRepository;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public void addProductToCart(long productId, long userId) {
        Product product = productService.getProductById(productId);
        User user = userService.getUserById(userId);

        UserCart userCart = new UserCart();
        userCart.setUser(user);
        userCart.setProductId(product.getId());
        userCart.setProductName(product.getName());
        userCart.setProductDescription(product.getDescription());
        userCart.setProductPrice(product.getPrice());
        userCart.setImageUrl(product.getImageUrl());

        userCartRepository.save(userCart);
        productService.deleteProduct(productId);
    }

    @Override
    public User getUserById(long userId) {
        return userService.getUserById(userId);
    }

    @Override
    public List<UserCart> getProductsFromUserCartByUserId(long userId) {
        List<UserCart> userCarts = userCartRepository.findByUserId(userId);
        return userCarts;
    }

    @Override
    public void removeProductFromCart(long userCartId) {
        userCartRepository.deleteById(userCartId);
    }

    @Override
    public UserCart getUserCartById(long userCartId) {
        return userCartRepository.findById(userCartId).orElse(null);
    }
}
