package com.guilledev.backend.categoria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.guilledev.backend.categoria.dto.CategoriaRequest;
import com.guilledev.backend.categoria.dto.CategoriaResponse;
import com.guilledev.backend.categoria.entity.Categoria;
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

    /*
     * Método guardar categoria, primero convierte el request en una entidad, de ahi
     * como id es null con save comunicamos
     * con hibernate y ve el null y hace un insert e hibernate toma ese id y lo
     * coloca dentro de la entidad(objeto), al final
     * convertimos la entidad en response para no pasar toda la entidad al cliente y
     * retornamos el response
     */
    @Override
    public CategoriaResponse guardar(CategoriaRequest request) {
        
        if (repository.existsByNombre(request.getNombre())) {
            throw new RuntimeException("La categoría ya existe");
        }

        Categoria categoria = mapper.toEntity(request);
        Categoria categoriaGuardada = repository.save(categoria);
        CategoriaResponse response = mapper.toResponse(categoriaGuardada);

        return response;
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
