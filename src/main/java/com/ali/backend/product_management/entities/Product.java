package com.ali.backend.product_management.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 25)
    @NotBlank
    private String name;
    @Size(max = 1000)
    private String description;
    @Positive
    private BigDecimal price;
    @Min(10)
    private int quantityOfStock;
    @CreationTimestamp //remplir automatiquement la date de création d’une entité au moment de l’insertion en base de données.
    @Column(updatable = false) //empêcher toute modification après la création
    private LocalDate dateCreation;
}
