package com.guilledev.backend.tipo_de_producto.service;

import java.util.List;

import com.guilledev.backend.tipo_de_producto.dto.TipoDeProductoRequest;
import com.guilledev.backend.tipo_de_producto.dto.TipoDeProductoResponse;

public interface TipoDeProductoService {

    TipoDeProductoResponse guardar(TipoDeProductoRequest request);

    List<TipoDeProductoResponse> listar();

    TipoDeProductoResponse buscarPorID(Long id);

    TipoDeProductoResponse actualizar(Long id, TipoDeProductoRequest request);

    void eliminar(Long id);

    void activar(Long id);

    List<TipoDeProductoResponse> buscarPorNombre(String nombre);
}
