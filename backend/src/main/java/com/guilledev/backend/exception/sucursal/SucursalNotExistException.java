package com.guilledev.backend.exception.sucursal;

public class SucursalNotExistException extends RuntimeException {
    public SucursalNotExistException(String mensaje) {
        super(mensaje);
    }

}
