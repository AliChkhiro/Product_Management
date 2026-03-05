package com.ali.backend.product_management.exceptions;

import com.ali.backend.product_management.dtos.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> methodArgumentNotValid(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(),
                fieldError.getDefaultMessage()));

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                "Erreur de validation",
                "Donnees invalides",
                400,
                LocalDateTime.now(),
                errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> methodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                "Type de paramètre invalide",
                "La valeur fournie dans l'URL n'est pas du bon type",
                400,
                LocalDateTime.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> exception(Exception ex){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                "Erreur interne du serveur",
                "Une erreur inattendue s'est produite. Veuillez réessayer plus tard.",
                500,
                LocalDateTime.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDTO);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> ResourceNotFound(ResourceNotFoundException ex){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                "Ressource introuvable",
                ex.getMessage(),
                404,
                LocalDateTime.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
    }


}
