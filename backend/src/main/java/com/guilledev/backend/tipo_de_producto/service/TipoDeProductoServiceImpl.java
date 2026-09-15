package com.guilledev.backend.tipo_de_producto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.guilledev.backend.exception.tipoproducto.TipoProductoDuplicadoException;
import com.guilledev.backend.exception.tipoproducto.TipoProductoNotFoundException;
import com.guilledev.backend.tipo_de_producto.dto.TipoDeProductoRequest;
import com.guilledev.backend.tipo_de_producto.dto.TipoDeProductoResponse;
import com.guilledev.backend.tipo_de_producto.entity.TipoDeProducto;
import com.guilledev.backend.tipo_de_producto.mapper.TipoDeProductoMapper;
import com.guilledev.backend.tipo_de_producto.repository.TipoDeProductoRepository;

@Service
public class TipoDeProductoServiceImpl implements TipoDeProductoService {

    private final TipoDeProductoRepository repository;
    private final TipoDeProductoMapper mapper;

    public TipoDeProductoServiceImpl(TipoDeProductoRepository repository,
            TipoDeProductoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TipoDeProductoResponse guardar(TipoDeProductoRequest request) {
        if (repository.existsByNombre(request.getNombre())) {
            throw new TipoProductoDuplicadoException(request.getNombre() + " ya existe");
        }
        TipoDeProducto tipoproducto = mapper.toEntity(request);
        TipoDeProducto tipoproductoGuardado = repository.save(tipoproducto);
        TipoDeProductoResponse response = mapper.toResponse(tipoproductoGuardado);
        return response;
    }

    @Override
    public List<TipoDeProductoResponse> listar() {
        List<TipoDeProducto> tipoproductos = repository.findAll();
        List<TipoDeProductoResponse> respuestas = new ArrayList<>();
        for (TipoDeProducto tipoproducto : tipoproductos) {
            respuestas.add(mapper.toResponse(tipoproducto));
        }
        return respuestas;
    }

    @Override
    public TipoDeProductoResponse buscarPorID(Long id) {
        Optional<TipoDeProducto> tipoproductoId = repository.findById(id);
        if (tipoproductoId.isEmpty()) {
            throw new TipoProductoNotFoundException(id);
        }
        TipoDeProducto entidad = tipoproductoId.get();
        TipoDeProductoResponse response = mapper.toResponse(entidad);
        return response;
    }

    @Override
    public TipoDeProductoResponse actualizar(Long id, TipoDeProductoRequest request) {
        Optional<TipoDeProducto> tipoproductoOptional = repository.findById(id);
        if (tipoproductoOptional.isEmpty()) {
            throw new TipoProductoNotFoundException(id);
        }
        TipoDeProducto tipoproducto = tipoproductoOptional.get();
        if (!request.getNombre().equals(tipoproducto.getNombre())) {
            Optional<TipoDeProducto> tipoproductoExistente = repository.findByNombre(request.getNombre());
            if (tipoproductoExistente.isPresent()) {
                throw new TipoProductoDuplicadoException(request.getNombre() + " ya existe");
            }
        }
        tipoproducto.setNombre(request.getNombre());
        tipoproducto.setDescripcion(request.getDescripcion());
        TipoDeProducto tipoproductoActualizado = repository.save(tipoproducto);
        return mapper.toResponse(tipoproductoActualizado);
    }

    @Override
    public void eliminar(Long id) {
        Optional<TipoDeProducto> tipoproductoOptional = repository.findById(id);
        if (tipoproductoOptional.isEmpty()) {
            throw new TipoProductoNotFoundException(id);
        }
        TipoDeProducto tipoproducto = tipoproductoOptional.get();
        tipoproducto.setActivo(false);
        repository.save(tipoproducto);
    }

    @Override
    public void activar(Long id) {
        Optional<TipoDeProducto> tipoproductoOptional = repository.findById(id);
        if (tipoproductoOptional.isEmpty()) {
            throw new TipoProductoNotFoundException(id);
        }
        TipoDeProducto tipoproducto = tipoproductoOptional.get();
        tipoproducto.setActivo(true);
        repository.save(tipoproducto);
    }

    @Override
    public List<TipoDeProductoResponse> buscarPorNombre(String nombre) {
        List<TipoDeProducto> tipoproductos = repository.findByNombreContainingIgnoreCase(nombre);
        return tipoproductos.stream().map(mapper::toResponse).toList();
    }

}
