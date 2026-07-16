package com.guilledev.backend.exception.Categoria;

public class CategoriaExistException extends RuntimeException {
    public CategoriaExistException(Long id) {
        super("La categoría con id " + id + " ya existe con ese nombre.");
    }
}
