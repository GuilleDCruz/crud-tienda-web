package com.guilledev.backend.categoria.mapper;

import org.springframework.stereotype.Component;

import com.guilledev.backend.categoria.dto.CategoriaRequest;
import com.guilledev.backend.categoria.dto.CategoriaResponse;
import com.guilledev.backend.categoria.entity.Categoria;

@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());
        categoria.setActivo(true);

        return categoria;
    }

    public CategoriaResponse toResponse(Categoria categoria) {
        CategoriaResponse response = new CategoriaResponse();
        response.setId(categoria.getId());

        response.setNombre(categoria.getNombre());

        response.setDescripcion(categoria.getDescripcion());

        response.setActivo(categoria.getActivo());

        return response;
    }
}
