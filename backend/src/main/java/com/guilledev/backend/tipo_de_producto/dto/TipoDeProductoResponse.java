package com.guilledev.backend.tipo_de_producto.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class TipoDeProductoResponse {

    private Long id;

    private String nombre;

    private String descripcion;

    private Boolean activo;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
