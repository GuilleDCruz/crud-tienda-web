package com.guilledev.backend.proveedor.service;

import java.util.List;

import com.guilledev.backend.proveedor.dto.ProveedorRequest;
import com.guilledev.backend.proveedor.dto.ProveedorResponse;

public interface ProveedorService {
    ProveedorResponse guardar(ProveedorRequest request);
    List<ProveedorResponse> listar();
    ProveedorResponse buscarPorID(Long id);
    ProveedorResponse actualizar(Long id, ProveedorRequest request);
    void eliminar(Long id);
    void activar(Long id);
    List<ProveedorResponse> buscarPorNombres(String nombre);
    
} 
