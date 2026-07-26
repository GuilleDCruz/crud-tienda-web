package com.guilledev.backend.exception.Categoria;

public class CategoriaNotFoundNameException extends RuntimeException{
    public CategoriaNotFoundNameException(String mensaje) {
        super(mensaje);
    }
}
