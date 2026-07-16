package com.guilledev.backend.exception.Categoria;

public class CategoriaDuplicadaException extends RuntimeException {
    public CategoriaDuplicadaException(String mensaje) {
        super("La categoría con nombre " + mensaje + " ya existe.");
    }
}

