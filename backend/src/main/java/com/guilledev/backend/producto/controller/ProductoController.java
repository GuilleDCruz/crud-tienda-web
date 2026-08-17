package com.guilledev.backend.producto.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilledev.backend.producto.dto.ProductoRequest;
import com.guilledev.backend.producto.dto.ProductoResponse;
import com.guilledev.backend.producto.service.ProductoService;
import com.guilledev.backend.proveedor.dto.ProveedorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/producto")
@Tag(name = "Producto", description = "Operaciones relacionadas con la gestion de productor")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Crear
    @Operation(summary = "Crear producto", description = "Crear un producto en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Los datos enviados no son validos"),
            @ApiResponse(responseCode = "409", description = "El producto ya existe")
    })

    @PostMapping
    public ProductoResponse guardar(@Valid @RequestBody ProductoRequest request) {
        return productoService.guardar(request);
    }

    // Actualizar
    @Operation(summary = "Actualiza un producto", description = "Actualizar producto en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "El producto no existe")
    })

    @PutMapping("{id}")
    public ProductoResponse actualizar(@Valid @PathVariable Long id, @RequestBody ProductoRequest request) {
        return productoService.actualizar(id, request);
    }

    // Listar todos
    @Operation(summary = "Listar productos", description = "Obtiene todos los prouctos del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos obtenidos correctamente")
    })

    @GetMapping
    public List<ProductoResponse> listar() {
        return productoService.listar();
    }

    // Listar por nombre
    @Operation(summary = "Listar un proveedor por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "El producto no existe")
    })

    @GetMapping("/buscar")
    public List<ProductoResponse> buscarPorNombre(@RequestParam String nombre) {
        return productoService.buscarPorNombre(nombre);
    }

    // Desactivar estado del producto
    @Operation(summary = "Desactivar un producto", description = "Desactiva un producto existente por medio del id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto desactivado correctamente"),
            @ApiResponse(responseCode = "404", description = "El producto no existe")
    })

    @DeleteMapping("{id}")
    public void elimnar(@PathVariable Long id) {
        productoService.eliminar(id);
    }

    // Activar el estado del producto
    @Operation(summary = "Activar un producto", description = "Activar un producto existente por medio del id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto activado correctamente"),
            @ApiResponse(responseCode = "404", description = "El producto no existe")
    })

    @PutMapping("{id}/activar")
    public void actiar(@PathVariable Long id) {
        productoService.activar(id);
    }

    // Buscar por nombres
    @Operation(summary = "Buscar productos por nombre", description = "Obtiene una lista de productos por su nombre y coincidencias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "No se encontraron productos")
    })

    @GetMapping("/buscarNombres")
    public List<ProductoResponse> buscarPorNombres(@RequestParam String nombre) {
        return productoService.buscarPorNombres(nombre);
    }

}
