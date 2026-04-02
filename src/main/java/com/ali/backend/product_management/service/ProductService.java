package com.ali.backend.product_management.service;

import com.ali.backend.product_management.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);
    Page<Product> findAllProducts(Pageable page);
    Optional<Product> findProductById(Long id);
    Product updateProduct(Product product);
    void deleteProduct(Long id);

}
