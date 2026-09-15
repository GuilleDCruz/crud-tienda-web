package com.guilledev.backend.exception.tipoproducto;

public class TipoProductoDuplicadoException extends RuntimeException {
    public TipoProductoDuplicadoException(String mensaje) {
        super(mensaje);
    }
    
}
