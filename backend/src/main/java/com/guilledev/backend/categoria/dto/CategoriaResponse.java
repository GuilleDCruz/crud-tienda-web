package com.guilledev.backend.categoria.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private Boolean activod;
}
