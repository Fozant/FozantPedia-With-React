package com.fauzan.springboot.springBootFauzan.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fauzan.springboot.springBootFauzan.model.Product;
import com.fauzan.springboot.springBootFauzan.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepo.findById(id);
        return product.orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

   

    @Override
    public void setImageUrl(Product product, String imageUrl) {
        product.setImageUrl(imageUrl);
        productRepo.save(product); 
    }

    @Override
    public String getImageUrl(Product product) {
        return product.getImageUrl();
    }
    @Override
    public List<Product> searchProducts(String keyword) {
        List<Product> allProducts = getAllProducts();
        return allProducts.stream()
                .filter(product -> product.getName().toLowerCase().contains(keyword.toLowerCase()) || product.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
    @Override
public List<Product> getSortedProducts(String sortField) {
    List<Product> allProducts = getAllProducts();
    switch (sortField) {
        case "name":
            allProducts.sort(Comparator.comparing(p -> p.getName().toLowerCase()));
            break;
        case "price":
            allProducts.sort(Comparator.comparing(Product::getPrice));
            break;
        
        default:
            
            allProducts.sort(Comparator.comparing(Product::getId));
            break;
    }
    return allProducts;
}

}
