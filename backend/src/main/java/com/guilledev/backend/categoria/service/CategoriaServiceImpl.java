package com.guilledev.backend.categoria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.guilledev.backend.categoria.dto.CategoriaRequest;
import com.guilledev.backend.categoria.dto.CategoriaResponse;
import com.guilledev.backend.categoria.entity.Categoria;
import com.guilledev.backend.categoria.mapper.CategoriaMapper;
import com.guilledev.backend.categoria.repository.CategoriaRepository;
import com.guilledev.backend.exception.Categoria.CategoriaDuplicadaException;
import com.guilledev.backend.exception.Categoria.CategoriaNotFoundException;

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
            throw new CategoriaDuplicadaException(request.getNombre());
        }
        Categoria categoria = mapper.toEntity(request);
        Categoria categoriaGuardada = repository.save(categoria);
        CategoriaResponse response = mapper.toResponse(categoriaGuardada);
        return response;
    }

    @Override
    public List<CategoriaResponse> listar() {
        List<Categoria> categorias = repository.findAll();
        List<CategoriaResponse> respuestas = new ArrayList<>();
        for (Categoria categoria : categorias) {
            respuestas.add(mapper.toResponse(categoria));
        }
        return respuestas;
    }

    @Override
    public CategoriaResponse buscarPorID(Long id) {
        Optional<Categoria> categoriaId = repository.findById(id);
        if (categoriaId.isEmpty()) {
            throw new CategoriaNotFoundException(id);
        }
        Categoria entidad = categoriaId.get();
        CategoriaResponse response = mapper.toResponse(entidad);
        return response;
    }

    @Override
    public CategoriaResponse actualizar(Long id, CategoriaRequest request) {
        // 1. Buscar la categoría por su ID
        Optional<Categoria> categoriaOptional = repository.findById(id);
        // 2. Si no existe, lanzar excepción
        if (categoriaOptional.isEmpty()) {
            throw new CategoriaNotFoundException(id);
        }
        // 3. Obtener la entidad del Optional
        Categoria entidad = categoriaOptional.get();
        // 4. Verificar si el nombre cambió
        if (!request.getNombre().equals(entidad.getNombre())) {
            // Buscar si ya existe otra categoría con ese nombre
            Optional<Categoria> categoriaExistente = repository.findByNombre(request.getNombre());
            if (categoriaExistente.isPresent()) {
                throw new CategoriaDuplicadaException(request.getNombre());
            }
        }
        // 5. Actualizar los datos
        entidad.setNombre(request.getNombre());
        entidad.setDescripcion(request.getDescripcion());
        // 6. Guardar los cambios
        Categoria categoriaActualizada = repository.save(entidad);
        // 7. Convertir Entity -> Response
        return mapper.toResponse(categoriaActualizada);
    }

    @Override
    public void eliminar(Long id) {
        Optional<Categoria> categoriaOptional = repository.findById(id);
        if (categoriaOptional.isEmpty()) {
            throw new CategoriaNotFoundException(id);
        }
        Categoria entidad = categoriaOptional.get();
        entidad.setActivo(false);
        repository.save(entidad);
    }

}
