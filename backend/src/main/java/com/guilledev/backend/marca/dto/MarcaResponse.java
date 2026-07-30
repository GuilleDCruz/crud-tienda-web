package com.guilledev.backend.marca.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarcaResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private Boolean activo;
}
