package com.guilledev.backend.exception.marca;

public class MarcaNotFoundException extends RuntimeException{
    public MarcaNotFoundException(Long id){
        super("La marca con id  " + id + " no existe.");
    }
}
