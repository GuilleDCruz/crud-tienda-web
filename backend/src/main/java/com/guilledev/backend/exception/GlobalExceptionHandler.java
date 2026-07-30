package com.guilledev.backend.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.guilledev.backend.exception.categoria.CategoriaDuplicadaException;
import com.guilledev.backend.exception.categoria.CategoriaNotFoundException;
import com.guilledev.backend.exception.marca.MarcaDuplicadaException;
import com.guilledev.backend.exception.marca.MarcaNotFoundException;
import com.guilledev.backend.exception.proveedor.ProveedorDuplicadoException;
import com.guilledev.backend.exception.proveedor.ProveedorNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> manejarValidacion(
                        MethodArgumentNotValidException ex) {

                ErrorResponse error = new ErrorResponse(
                                LocalDateTime.now(),
                                HttpStatus.BAD_REQUEST.value(),
                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                "Rellene todos los campos requeridos.");

                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(error);
        }

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

        @ExceptionHandler(MarcaNotFoundException.class)
        public ResponseEntity<?> manejarMarcaNoEncontrada(
                        MarcaNotFoundException ex) {
                ErrorResponse error = new ErrorResponse(
                                LocalDateTime.now(),
                                HttpStatus.NOT_FOUND.value(),
                                HttpStatus.NOT_FOUND.getReasonPhrase(),
                                ex.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(error);
        }

        @ExceptionHandler(MarcaDuplicadaException.class)
        public ResponseEntity<ErrorResponse> marcaDuplicada(
                        MarcaDuplicadaException ex) {
                ErrorResponse error = new ErrorResponse(
                                LocalDateTime.now(),
                                HttpStatus.CONFLICT.value(),
                                HttpStatus.CONFLICT.getReasonPhrase(),
                                ex.getMessage());
                return ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .body(error);
        }

        @ExceptionHandler(ProveedorNotFoundException.class)
        public ResponseEntity<?> manejarMarcaNoEncontrada(
                        ProveedorNotFoundException ex) {
                ErrorResponse error = new ErrorResponse(
                                LocalDateTime.now(),
                                HttpStatus.NOT_FOUND.value(),
                                HttpStatus.NOT_FOUND.getReasonPhrase(),
                                ex.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(error);
        }

        @ExceptionHandler(ProveedorDuplicadoException.class)
        public ResponseEntity<ErrorResponse> marcaDuplicada(
                        ProveedorDuplicadoException ex) {
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
