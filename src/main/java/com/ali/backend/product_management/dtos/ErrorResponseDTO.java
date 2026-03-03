package com.ali.backend.product_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {

    private String error;
    private String message;
    private int status;
    private LocalDateTime timestamp;
    private Map<String, String> validationErrors;
}
