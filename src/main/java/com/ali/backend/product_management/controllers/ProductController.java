package com.ali.backend.product_management.controllers;

import com.ali.backend.product_management.dtos.ProductRequestDTO;
import com.ali.backend.product_management.dtos.ProductResponseDTO;
import com.ali.backend.product_management.entities.Product;
import com.ali.backend.product_management.mappers.ProductMapper;
import com.ali.backend.product_management.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
//@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;


    public ProductController(ProductService productService, ProductMapper productMapper){
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public List<Product> allProducts(){
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return productService.findProductById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductRequestDTO productRequestDTO){
        Product product = productMapper.toEntity(productRequestDTO);
        Product savedProduct = productService.createProduct(product);
        ProductResponseDTO responseDTO = productMapper.responseDTO(savedProduct);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO productRequestDTO){
        Optional<Product> optionalProduct = productService.findProductById(id);
        if (optionalProduct.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Product existingProduct = optionalProduct.get();
        productMapper.updateEntityFromDto(productRequestDTO, existingProduct);
        Product updatedProduct = productService.updateProduct(existingProduct);
        ProductResponseDTO responseDTO = productMapper.responseDTO(updatedProduct);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductRequestDTO> delete(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
