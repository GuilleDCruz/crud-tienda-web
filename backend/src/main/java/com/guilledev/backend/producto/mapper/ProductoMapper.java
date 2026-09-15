package com.guilledev.backend.producto.mapper;

import org.springframework.stereotype.Component;

import com.guilledev.backend.producto.dto.ProductoRequest;
import com.guilledev.backend.producto.dto.ProductoResponse;
import com.guilledev.backend.producto.entity.Producto;

@Component
public class ProductoMapper {

    public Producto toEntity(ProductoRequest request) {
        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        producto.setActivo(true);
        return producto;
    }

    public ProductoResponse toResponse(Producto producto) {
        ProductoResponse response = new ProductoResponse();
        response.setId(producto.getId());
        response.setNombre(producto.getNombre());
        response.setDescripcion(producto.getDescripcion());
        response.setPrecio(producto.getPrecio());
        response.setStock(producto.getStock());
        response.setActivo(producto.getActivo());
        response.setCreatedAt(producto.getCreatedAt());
        response.setUpdatedAt(producto.getUpdatedAt());

        response.setCategoriaId(producto.getCategoria().getId());
        response.setCategoriaNombre(producto.getCategoria().getNombre());

        response.setMarcaId(producto.getMarca().getId());
        response.setMarcaNombre(producto.getMarca().getNombre());

        response.setProveedorId(producto.getProveedor().getId());
        response.setProveedorNombre(producto.getProveedor().getNombre());

        response.setTipoDeProductoId(producto.getTipoDeProducto().getId());
        response.setTipoDeProductoNombre(producto.getTipoDeProducto().getNombre());

        return response;
    }

}
