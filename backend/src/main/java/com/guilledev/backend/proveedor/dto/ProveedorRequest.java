package com.guilledev.backend.proveedor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProveedorRequest {
    @NotBlank
    @Size(max = 100)
    private String nombre;
    @Size(max = 100)
    private String descripcion;
}
