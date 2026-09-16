package com.guilledev.backend.exception.proveedor;

public class ProveedorNotFoundNameException extends RuntimeException {
    public ProveedorNotFoundNameException(String mensaje) {
        super(mensaje);
    }
}
