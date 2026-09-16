package com.guilledev.backend.sucursal.service;

import java.util.List;

import com.guilledev.backend.sucursal.dto.SucursalRequest;
import com.guilledev.backend.sucursal.dto.SucursalResponse;

public interface SucursalService {
    SucursalResponse guardar(SucursalRequest request);

    List<SucursalResponse> listar();

    SucursalResponse buscarPorID(Long id);

    SucursalResponse actualizar(Long id, SucursalRequest request);

    void eliminar(Long id);

    void activar(Long id);

    List<SucursalResponse> buscarPorNombres(String nombre);
}
