package com.guilledev.backend.exception.proveedor;

public class ProveedorNotFoundException extends RuntimeException {
    public ProveedorNotFoundException(Long id) {
        super("El proveedor con id " + id + " no existe.");
    }
}
