package com.guilledev.backend.exception.sucursal;

public class SucursalDuplicadoException extends RuntimeException {
    public SucursalDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
