package com.fauzan.springboot.springBootFauzan.service;

import java.util.List;

import com.fauzan.springboot.springBootFauzan.model.Product;

public interface ProductService {
    Product save(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void deleteProduct(Long id);
    void setImageUrl(Product product, String imageUrl);
    String getImageUrl(Product product);
    List<Product> searchProducts(String keyword);
    List<Product> getSortedProducts(String sortField);
}
