package com.guilledev.backend.exception.marca;

public class MarcaDuplicadaException extends RuntimeException{
    public MarcaDuplicadaException(String mensaje){
        super(mensaje);
    }
}
