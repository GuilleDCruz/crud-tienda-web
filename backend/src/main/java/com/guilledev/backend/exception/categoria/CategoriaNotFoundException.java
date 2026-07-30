package com.guilledev.backend.exception.categoria;

public class CategoriaNotFoundException extends RuntimeException {
    public CategoriaNotFoundException(Long id) {
        super("La categoría con id " + id + " no existe.");
    }
}
