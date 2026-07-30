package com.guilledev.backend.proveedor.mapper;

import org.springframework.stereotype.Component;

import com.guilledev.backend.proveedor.dto.ProveedorRequest;
import com.guilledev.backend.proveedor.dto.ProveedorResponse;
import com.guilledev.backend.proveedor.entity.Proveedor;

@Component
public class ProveedorMapper {
    
    public Proveedor toEntity(ProveedorRequest request){
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(request.getNombre());
        proveedor.setDescripcion(request.getDescripcion());
        proveedor.setActivo(true);
        return proveedor;
    }

    public ProveedorResponse toResponse(Proveedor proveedor){
        ProveedorResponse response = new ProveedorResponse();
        response.setId(proveedor.getId());
        response.setNombre(proveedor.getNombre());
        response.setDescripcion(proveedor.getDescripcion());
        response.setActivo(proveedor.getActivo());
        return response;
    }
}
