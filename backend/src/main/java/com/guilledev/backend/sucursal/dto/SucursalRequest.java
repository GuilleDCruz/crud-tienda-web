package com.guilledev.backend.sucursal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SucursalRequest {
    @NotBlank
    @Size(max = 100)
    private String nombre;
    @Size(max = 50)
    private String codigo;
    @Size(max = 250)
    private String direccion;
    @Size(max = 20)
    private String telefono;
    @Size(max = 100)
    private String email;
    private Boolean activo;
}
