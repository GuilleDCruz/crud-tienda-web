package com.guilledev.backend.proveedor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.guilledev.backend.exception.proveedor.ProveedorDuplicadoException;
import com.guilledev.backend.exception.proveedor.ProveedorNotExistException;
import com.guilledev.backend.exception.proveedor.ProveedorNotFoundException;
import com.guilledev.backend.exception.proveedor.ProveedorNotFoundNameException;
import com.guilledev.backend.marca.dto.MarcaRequest;
import com.guilledev.backend.proveedor.dto.ProveedorRequest;
import com.guilledev.backend.proveedor.dto.ProveedorResponse;
import com.guilledev.backend.proveedor.entity.Proveedor;
import com.guilledev.backend.proveedor.mapper.ProveedorMapper;
import com.guilledev.backend.proveedor.repository.ProveedorRepository;

@Service
public class ProveedorServiceImpl implements ProveedorService {
    private final ProveedorRepository repository;
    private final ProveedorMapper mapper;

    public ProveedorServiceImpl(ProveedorRepository repository, ProveedorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProveedorResponse guardar(ProveedorRequest request) {
        if (repository.existsByNombre(request.getNombre())) {
            throw new ProveedorDuplicadoException(request.getNombre());
        }
        Proveedor proveedor = mapper.toEntity(request);
        Proveedor proveedorGuardado = repository.save(proveedor);
        ProveedorResponse response = mapper.toResponse(proveedorGuardado);
        return response;
    }

    @Override
    public List<ProveedorResponse> listar() {
        List<Proveedor> proveedores = repository.findAll();
        List<ProveedorResponse> respuesta = new ArrayList<>();
        for (Proveedor proveedor : proveedores) {
            respuesta.add(mapper.toResponse(proveedor));
        }
        return respuesta;
    }

    @Override
    public ProveedorResponse buscarPorID(Long id) {
        Optional<Proveedor> proveedorId = repository.findById(id);
        if (proveedorId.isEmpty()) {
            throw new ProveedorNotFoundException(id);
        }
        Proveedor entidad = proveedorId.get();
        ProveedorResponse response = mapper.toResponse(entidad);
        return response;
    }

    @Override
    public ProveedorResponse actualizar(Long id, ProveedorRequest request) {
        Optional<Proveedor> proveedorOptional = repository.findById(id);
        if (proveedorOptional.isEmpty()) {
            throw new ProveedorNotFoundException(id);
        }
        Proveedor entidad = proveedorOptional.get();
        if (!request.getNombre().equals(entidad.getNombre())) {
            Optional<Proveedor> proveedorExistente = repository.findByNombre(request.getNombre());
            if (proveedorExistente.isPresent()) {
                throw new ProveedorDuplicadoException(request.getNombre());
            }
        }
        entidad.setNombre(request.getNombre());
        entidad.setDescripcion(request.getDescripcion());
        Proveedor proveedorActualizado = repository.save(entidad);
        return mapper.toResponse(proveedorActualizado);
    }

    @Override
    public void eliminar(Long id) {
        Optional<Proveedor> proveedorOptional = repository.findById(id);
        if (proveedorOptional.isEmpty()) {
            throw new ProveedorNotFoundException(id);
        }
        Proveedor entidad = proveedorOptional.get();
        entidad.setActivo(false);
        repository.save(entidad);
    }

    @Override
    public void activar(Long id) {
        Optional<Proveedor> proveedorOptional = repository.findById(id);
        if (proveedorOptional.isEmpty()) {
            throw new ProveedorNotExistException("El proveedor que busca no existe");
        }
        Proveedor entidad = proveedorOptional.get();
        entidad.setActivo(true);
        repository.save(entidad);
    }

    @Override
    public List<ProveedorResponse> buscarPorNombres(String nombre) {
        List<Proveedor> proveedores = repository.findByNombreContainingIgnoreCase(nombre);
        return proveedores.stream().map(mapper::toResponse).toList();
    }
}
