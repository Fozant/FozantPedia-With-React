package com.fauzan.springboot.springBootFauzan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import com.fauzan.springboot.springBootFauzan.model.Product;
import com.fauzan.springboot.springBootFauzan.service.ProductService;

@RestController
@RequestMapping("/api")
public class MainController {
    private final ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/products/search")
    public List<Product> searchProducts(@RequestBody Map<String, String> searchData) {
        String keyword = searchData.get("keyword");
        return productService.searchProducts(keyword);
    }

    //change test
}
