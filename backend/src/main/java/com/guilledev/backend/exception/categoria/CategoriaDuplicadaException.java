package com.guilledev.backend.exception.categoria;

public class CategoriaDuplicadaException extends RuntimeException {
    public CategoriaDuplicadaException(String mensaje) {
        super(mensaje);
    }
}

