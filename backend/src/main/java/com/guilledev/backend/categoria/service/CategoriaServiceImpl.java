package com.guilledev.backend.categoria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.guilledev.backend.categoria.dto.CategoriaRequest;
import com.guilledev.backend.categoria.dto.CategoriaResponse;
import com.guilledev.backend.categoria.mapper.CategoriaMapper;
import com.guilledev.backend.categoria.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;    
    
    public CategoriaServiceImpl(CategoriaRepository repository, CategoriaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CategoriaResponse guardar(CategoriaRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public List<CategoriaResponse> listar() {
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    @Override
    public CategoriaResponse buscarPorID(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorID'");
    }

    @Override
    public CategoriaResponse actualizar(Long id, CategoriaRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public void eliminar(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

}
