package com.fauzan.springboot.springBootFauzan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.fauzan.springboot.springBootFauzan.model.Product;
import com.fauzan.springboot.springBootFauzan.service.ProductService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class ProductController {
    private final ProductService productService;
    private final String uploadDir = "D:/coding/springBootFauzan (1)coba simpan di file ver lama/springBootFauzan (1)coba simpan di file/springBootFauzan/src/main/resources/static/images/";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/addProduct")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute("product") Product product, @RequestParam("image") MultipartFile image,
                              RedirectAttributes redirectAttributes, Model model) {
        if (!image.isEmpty()) {
            try {
                String originalFileName = image.getOriginalFilename();
                String fileName = UUID.randomUUID() + originalFileName;
                Path path = Paths.get(uploadDir + fileName);
                Files.write(path, image.getBytes());
                product.setImageUrl("/images/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception appropriately
            }
        }
        productService.save(product);
        model.addAttribute("success", true);
        return "redirect:/home";
    }

    @PostMapping("/products/update")
    public String showFormForUpdate(@RequestParam("productId") long id, @ModelAttribute Product updatedProduct, Model model) {
    Product existingProduct = productService.getProductById(id);
    updatedProduct.setId(existingProduct.getId());
    
    updatedProduct.setImageUrl(existingProduct.getImageUrl());
    productService.save(updatedProduct);
    model.addAttribute("success", true);
    return "updateProduct"; 
}
    @PostMapping("/products/update/save")
    public String saveUpdatedProduct(@ModelAttribute Product product, Model model) {
    // Retrieve the existing product by ID
    Product existingProduct = productService.getProductById(product.getId());

    // Check if the existing product's imageUrl is not null and set it to the updated product
    if (existingProduct.getImageUrl() != null) {
        product.setImageUrl(existingProduct.getImageUrl());
    }

    // Save the updated product
    productService.save(product);

    model.addAttribute("success", true);
    return "redirect:/home";
}





    @PostMapping("/products/delete")
    public String delete(@RequestParam("productId") long id) {
        productService.deleteProduct(id);
        return "redirect:/home";
    }
}
