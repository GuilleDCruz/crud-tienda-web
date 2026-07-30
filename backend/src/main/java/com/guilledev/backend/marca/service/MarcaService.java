package com.guilledev.backend.marca.service;

import java.util.List;

import com.guilledev.backend.marca.dto.MarcaRequest;
import com.guilledev.backend.marca.dto.MarcaResponse;

public interface MarcaService {
    MarcaResponse guardar(MarcaRequest request);
    List<MarcaResponse> listar();
    MarcaResponse buscarPorID(Long id);
    MarcaResponse actualizar(Long id, MarcaRequest request);
    void eliminar(Long id);
    void activar(Long id);
    MarcaResponse buscarPorNombre(String nombre);
    
}
