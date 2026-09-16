package com.guilledev.backend.sucursal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SucursalResponse {
    private Long id;
    private String nombre;
    private String codigo;
    private String direccion;
    private String telefono;
    private String email;
    private Boolean activo;
}
