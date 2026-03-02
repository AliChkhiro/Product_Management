package com.ali.backend.product_management.mappers;

import com.ali.backend.product_management.dtos.ProductRequestDTO;
import com.ali.backend.product_management.dtos.ProductResponseDTO;
import com.ali.backend.product_management.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    // DTO → Entity
    public Product toEntity(ProductRequestDTO requestDTO){
        Product product = new Product();
        product.setName(requestDTO.getName());
        product.setDescription(requestDTO.getDescription());
        product.setPrice(requestDTO.getPrice());
        product.setQuantityOfStock(requestDTO.getQuantityOfStock());
        return product;
    }

    // Entity → ResponseDTO
    public ProductResponseDTO responseDTO(Product product){
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setQuantityOfStock(product.getQuantityOfStock());
        dto.setDateCreation(product.getDateCreation());
        return dto;
    }

    public Product updateEntityFromDto(ProductRequestDTO productRequestDTO, Product product){
        product.setName(productRequestDTO.getName());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setQuantityOfStock(productRequestDTO.getQuantityOfStock());
        return product;
    }


}
