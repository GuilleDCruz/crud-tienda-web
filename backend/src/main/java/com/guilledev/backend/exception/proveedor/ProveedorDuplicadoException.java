package com.guilledev.backend.exception.proveedor;

public class ProveedorDuplicadoException extends RuntimeException{
    public ProveedorDuplicadoException(String mensaje){
        super(mensaje);
    }
}
