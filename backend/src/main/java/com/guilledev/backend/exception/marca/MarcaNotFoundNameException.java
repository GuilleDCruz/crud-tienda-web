package com.guilledev.backend.exception.marca;

public class MarcaNotFoundNameException extends RuntimeException{
    public MarcaNotFoundNameException(String mensaje){
        super(mensaje);
    }
}
