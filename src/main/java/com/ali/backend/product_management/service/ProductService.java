package com.ali.backend.product_management.service;

import com.ali.backend.product_management.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> findAllProducts();
    Optional<Product> findProductById(Long id);
    Product updateProduct(Product product);
    void deleteProduct(Long id);

}
