package com.guilledev.backend.sucursal.mapper;

import org.springframework.stereotype.Component;

import com.guilledev.backend.sucursal.dto.SucursalRequest;
import com.guilledev.backend.sucursal.dto.SucursalResponse;
import com.guilledev.backend.sucursal.entity.Sucursal;

@Component
public class SucursalMapper {

    public Sucursal toEntity(SucursalRequest request) {
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(request.getNombre());
        sucursal.setCodigo(request.getCodigo());
        sucursal.setDireccion(request.getDireccion());
        sucursal.setTelefono(request.getTelefono());
        sucursal.setEmail(request.getEmail());
        sucursal.setActivo(true);
        return sucursal;
    }

    public SucursalResponse toResponse(Sucursal sucursal) {
        SucursalResponse response = new SucursalResponse();
        response.setId(sucursal.getId());
        response.setNombre(sucursal.getNombre());
        response.setCodigo(sucursal.getCodigo());
        response.setDireccion(sucursal.getDireccion());
        response.setTelefono(sucursal.getTelefono());
        response.setEmail(sucursal.getEmail());
        response.setActivo(sucursal.getActivo());
        return response;
    }
}
