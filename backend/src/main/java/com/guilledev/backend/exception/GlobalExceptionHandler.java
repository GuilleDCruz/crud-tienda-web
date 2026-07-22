package com.guilledev.backend.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.guilledev.backend.exception.Categoria.CategoriaDuplicadaException;
import com.guilledev.backend.exception.Categoria.CategoriaNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoriaNotFoundException.class)
    public ResponseEntity<?> manejarCategoriaNoEncontrada(
            CategoriaNotFoundException ex) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(CategoriaDuplicadaException.class)
    public ResponseEntity<ErrorResponse> categoriaDuplicada(
            CategoriaDuplicadaException ex) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage());
                
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }
}
