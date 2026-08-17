package com.guilledev.backend.exception.producto;

public class ProductoDuplicadoException extends RuntimeException{
    public ProductoDuplicadoException(String mensaje){
        super(mensaje);
    }
    
}
