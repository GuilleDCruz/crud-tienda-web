package com.guilledev.backend.exception.marca;

public class MarcaNotExistException extends RuntimeException{
    public MarcaNotExistException(String mensaje){
        super(mensaje);
    }
}
