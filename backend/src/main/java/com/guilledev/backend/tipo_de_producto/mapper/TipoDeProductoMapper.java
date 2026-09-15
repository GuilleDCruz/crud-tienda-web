package com.guilledev.backend.tipo_de_producto.mapper;

import org.springframework.stereotype.Component;

import com.guilledev.backend.tipo_de_producto.dto.TipoDeProductoRequest;
import com.guilledev.backend.tipo_de_producto.dto.TipoDeProductoResponse;
import com.guilledev.backend.tipo_de_producto.entity.TipoDeProducto;

@Component 
public class TipoDeProductoMapper {

    public TipoDeProducto toEntity(TipoDeProductoRequest request) {
        TipoDeProducto tipoDeProducto = new TipoDeProducto();
        tipoDeProducto.setNombre(request.getNombre());
        tipoDeProducto.setDescripcion(request.getDescripcion());
        tipoDeProducto.setActivo(true);

        return tipoDeProducto;
    }

    public TipoDeProductoResponse toResponse(TipoDeProducto tipoDeProducto) {
        TipoDeProductoResponse response = new TipoDeProductoResponse();
        response.setId(tipoDeProducto.getId());
        response.setNombre(tipoDeProducto.getNombre());
        response.setDescripcion(tipoDeProducto.getDescripcion());
        response.setActivo(tipoDeProducto.getActivo());

        return response;
    }
}
