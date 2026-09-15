package com.guilledev.backend.tipo_de_producto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class TipoDeProductoRequest {
    @NotBlank 
    @Size (max = 100)
    private String nombre;

    @Size (max = 200)
    private String descripcion;

}
