package com.guilledev.backend.exception.tipoproducto;

public class TipoProductoNotFoundException extends RuntimeException {
    public TipoProductoNotFoundException(Long id) {
        super("Tipo de producto no encontrado con ID: " + id);
    }
    
}
