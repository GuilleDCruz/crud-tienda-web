package com.guilledev.backend.exception.proveedor;

public class ProveedorNotExistException extends RuntimeException{
    public ProveedorNotExistException(String mensaje){
        super(mensaje);
    }
}