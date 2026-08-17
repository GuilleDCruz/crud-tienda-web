package com.guilledev.backend.producto.service;

import java.util.List;

import com.guilledev.backend.producto.dto.ProductoRequest;
import com.guilledev.backend.producto.dto.ProductoResponse;

public interface ProductoService {
    ProductoResponse guardar(ProductoRequest productoRequest);
    List<ProductoResponse> listar();
    List<ProductoResponse> buscarPorNombre(String nombre);
    ProductoResponse actualizar(Long id, ProductoRequest request);
    ProductoResponse eliminar(Long id);
    ProductoResponse activar(Long id);
    List<ProductoResponse> buscarPorNombres(String nombre);

}
