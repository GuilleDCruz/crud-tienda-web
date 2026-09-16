package com.guilledev.backend.sucursal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.guilledev.backend.exception.sucursal.SucursalDuplicadoException;
import com.guilledev.backend.exception.sucursal.SucursalNotFoundException;
import com.guilledev.backend.sucursal.dto.SucursalRequest;
import com.guilledev.backend.sucursal.dto.SucursalResponse;
import com.guilledev.backend.sucursal.entity.Sucursal;
import com.guilledev.backend.sucursal.mapper.SucursalMapper;
import com.guilledev.backend.sucursal.repository.SucursalRepository;

@Service
public class SucursalSeriveImpl implements SucursalService {
    private final SucursalRepository repository;
    private final SucursalMapper mapper;

    public SucursalSeriveImpl(SucursalRepository repository, SucursalMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public SucursalResponse guardar(SucursalRequest request) {
        if (repository.existsByNombre(request.getNombre())) {
            throw new SucursalDuplicadoException(request.getNombre());
        }
        Sucursal sucursal = mapper.toEntity(request);
        Sucursal sucursalGuardada = repository.save(sucursal);
        SucursalResponse response = mapper.toResponse(sucursalGuardada);
        return response;
    }

    @Override
    public List<SucursalResponse> listar() {
        List<Sucursal> sucursales = repository.findAll();
        List<SucursalResponse> respuesta = new ArrayList<>();
        for (Sucursal sucursal : sucursales) {
            respuesta.add(mapper.toResponse(sucursal));
        }
        return respuesta;
    }

    @Override
    public SucursalResponse buscarPorID(Long id) {
        Optional<Sucursal> sucursalOptional = repository.findById(id);
        if (sucursalOptional.isEmpty()) {
            throw new SucursalNotFoundException(id);
        }
        Sucursal sucursal = sucursalOptional.get();
        return mapper.toResponse(sucursal);
    }

    @Override
    public SucursalResponse actualizar(Long id, SucursalRequest request) {
        Optional<Sucursal> sucursalOptional = repository.findById(id);
        if (sucursalOptional.isEmpty()) {
            throw new SucursalNotFoundException(id);
        }
        Sucursal sucursal = sucursalOptional.get();
        if (!request.getNombre().equals(sucursal.getNombre())) {
            Optional<Sucursal> sucursalExistente = repository.findByNombre(request.getNombre());
            if (sucursalExistente.isPresent()) {
                throw new SucursalDuplicadoException(request.getNombre());
            }
        }
        sucursal.setNombre(request.getNombre());
        sucursal.setCodigo(request.getCodigo());
        sucursal.setDireccion(request.getDireccion());
        sucursal.setTelefono(request.getTelefono());
        sucursal.setEmail(request.getEmail());
        Sucursal sucursalActualizada = repository.save(sucursal);
        return mapper.toResponse(sucursalActualizada);
    }

    @Override
    public void eliminar(Long id) {
        Optional<Sucursal> sucursalOptional = repository.findById(id);
        if (sucursalOptional.isEmpty()) {
            throw new SucursalNotFoundException(id);
        }
        Sucursal sucursal = sucursalOptional.get();
        sucursal.setActivo(false);
        repository.save(sucursal);
    }

    @Override
    public void activar(Long id) {
        Optional<Sucursal> sucursalOptional = repository.findById(id);
        if (sucursalOptional.isEmpty()) {
            throw new SucursalNotFoundException(id);
        }
        Sucursal sucursal = sucursalOptional.get();
        sucursal.setActivo(true);
        repository.save(sucursal);
    }

    @Override
    public List<SucursalResponse> buscarPorNombres(String nombre) {
        List<Sucursal> sucursales = repository.findByNombreContainingIgnoreCase(nombre);
        return sucursales.stream().map(mapper::toResponse).toList();
    }

}
