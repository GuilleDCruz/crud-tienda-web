package com.guilledev.backend.exception.sucursal;

public class SucursalNotFoundException extends RuntimeException {
    public SucursalNotFoundException(Long id) {
        super("La sucursal con id " + id + " no existe.");
    }
}
