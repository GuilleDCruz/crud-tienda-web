package com.guilledev.backend.categoria.service;

import java.util.List;

import com.guilledev.backend.categoria.dto.CategoriaRequest;
import com.guilledev.backend.categoria.dto.CategoriaResponse;

public interface CategoriaService {

    CategoriaResponse guardar(CategoriaRequest request);

    List<CategoriaResponse> listar();

    CategoriaResponse buscarPorID(Long id);

    CategoriaResponse actualizar(Long id, CategoriaRequest request);

    void eliminar(Long id);

}
