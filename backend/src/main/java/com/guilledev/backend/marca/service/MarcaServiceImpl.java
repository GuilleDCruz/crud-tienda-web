package com.guilledev.backend.marca.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.guilledev.backend.categoria.dto.CategoriaResponse;
import com.guilledev.backend.exception.marca.MarcaDuplicadaException;
import com.guilledev.backend.exception.marca.MarcaNotExistException;
import com.guilledev.backend.exception.marca.MarcaNotFoundException;
import com.guilledev.backend.exception.marca.MarcaNotFoundNameException;
import com.guilledev.backend.marca.dto.MarcaRequest;
import com.guilledev.backend.marca.dto.MarcaResponse;
import com.guilledev.backend.marca.entity.Marca;
import com.guilledev.backend.marca.mapper.MarcaMapper;
import com.guilledev.backend.marca.repository.MarcaRepository;

@Service
public class MarcaServiceImpl implements MarcaService {
    private final MarcaRepository repository;
    private final MarcaMapper mapper;

    public MarcaServiceImpl(MarcaRepository repository, MarcaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MarcaResponse guardar(MarcaRequest request) {
        if (repository.existsByNombre(request.getNombre())) {
            throw new MarcaDuplicadaException(request.getNombre());
        }
        Marca marca = mapper.toEntity(request);
        Marca marcaGuardada = repository.save(marca);
        MarcaResponse response = mapper.toResponse(marcaGuardada);
        return response;
    }

    @Override
    public List<MarcaResponse> listar() {
        List<Marca> marcas = repository.findAll();
        List<MarcaResponse> respuesta = new ArrayList<>();
        for (Marca marca : marcas) {
            respuesta.add(mapper.toResponse(marca));
        }
        return respuesta;
    }

    @Override
    public MarcaResponse buscarPorID(Long id) {
        Optional<Marca> marcaId = repository.findById(id);
        if (marcaId.isEmpty()) {
            throw new MarcaNotFoundException(id);
        }
        Marca entidad = marcaId.get();
        MarcaResponse response = mapper.toResponse(entidad);
        return response;
    }

    @Override
    public MarcaResponse actualizar(Long id, MarcaRequest request) {
        Optional<Marca> marcaOptional = repository.findById(id);
        if (marcaOptional.isEmpty()) {
            throw new MarcaNotFoundException(id);
        }
        Marca entidad = marcaOptional.get();
        if (!request.getNombre().equals(entidad.getNombre())) {
            Optional<Marca> marcaExistente = repository.findByNombre(request.getNombre());
            if (marcaExistente.isPresent()) {
                throw new MarcaDuplicadaException(request.getNombre());
            }
        }
        entidad.setNombre(request.getNombre());
        entidad.setDescripcion(request.getDescripcion());
        Marca marcaActualizada = repository.save(entidad);
        return mapper.toResponse(marcaActualizada);
    }

    @Override
    public void eliminar(Long id) {
        Optional<Marca> marcaOptional = repository.findById(id);
        if (marcaOptional.isEmpty()) {
            throw new MarcaNotFoundException(id);
        }
        Marca entidad = marcaOptional.get();
        entidad.setActivo(false);
        repository.save(entidad);
    }

    @Override
    public void activar(Long id) {
        Optional<Marca> marcaOptional = repository.findById(id);
        if (marcaOptional.isEmpty()) {
            throw new MarcaNotExistException("La marca que busca no existe");
        }
        Marca entidad = marcaOptional.get();
        entidad.setActivo(true);
        repository.save(entidad);
    }

    @Override
    public List<MarcaResponse> buscarPorNombres(String nombre) {
        List<Marca> marcas = repository.findByNombreContainingIgnoreCase(nombre);
        return marcas.stream().map(mapper::toResponse).collect(java.util.stream.Collectors.toList());
    }

}
