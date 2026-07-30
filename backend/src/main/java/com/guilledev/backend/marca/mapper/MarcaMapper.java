package com.guilledev.backend.marca.mapper;

import org.springframework.stereotype.Component;

import com.guilledev.backend.marca.dto.MarcaRequest;
import com.guilledev.backend.marca.dto.MarcaResponse;
import com.guilledev.backend.marca.entity.Marca;

@Component
public class MarcaMapper {
    
    public Marca toEntity(MarcaRequest request){
        Marca marca = new Marca();
        marca.setNombre(request.getNombre());
        marca.setDescripcion(request.getDescripcion());
        marca.setActivo(true);
        return marca;
    }

    public MarcaResponse toResponse(Marca marca){
        MarcaResponse response = new MarcaResponse();
        response.setId(marca.getId());
        response.setNombre(marca.getNombre());
        response.setDescripcion(marca.getDescripcion());
        response.setActivo(marca.getActivo());
        return response;
    }
}
