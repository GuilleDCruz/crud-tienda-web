package com.guilledev.backend.producto.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoResponse {

    private Long id;

    private String nombre;

    private String descripcion;

    private BigDecimal precio;

    private Integer stock;

    private Boolean activo;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long categoriaId;
    private String categoriaNombre;

    private Long marcaId;
    private String marcaNombre;

    private Long proveedorId;
    private String proveedorNombre;

    private Long tipoDeProductoId;
    private String tipoDeProductoNombre;

}
