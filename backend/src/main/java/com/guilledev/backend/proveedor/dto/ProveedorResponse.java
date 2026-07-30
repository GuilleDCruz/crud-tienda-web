package com.guilledev.backend.proveedor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProveedorResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private Boolean activo;
}
