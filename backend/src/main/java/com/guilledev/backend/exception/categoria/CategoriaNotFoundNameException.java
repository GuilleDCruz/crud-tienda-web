package com.guilledev.backend.exception.categoria;

public class CategoriaNotFoundNameException extends RuntimeException{
    public CategoriaNotFoundNameException(String mensaje) {
        super(mensaje);
    }
}
