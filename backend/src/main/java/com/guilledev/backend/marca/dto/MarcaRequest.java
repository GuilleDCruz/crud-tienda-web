package com.guilledev.backend.marca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarcaRequest {
    @NotBlank
    @Size(max = 100)

    private String nombre;

    @Size(max = 200)
    private String descripcion;
}
