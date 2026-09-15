package com.guilledev.backend.producto.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoRequest {

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @Size(max = 100)
    private String descripcion;

    @NotNull
    @DecimalMin(value = "0.01", inclusive = false)
    private BigDecimal precio;

    @NotNull
    @Min(0)
    private Integer stock;

    @NotNull
    private Long categoriaId;

    @NotNull
    private Long marcaId;

    @NotNull
    private Long proveedorId;

    @NotNull
    private Long tipoDeProductoId;

}
