package com.guilledev.backend.tipo_de_producto.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guilledev.backend.tipo_de_producto.dto.TipoDeProductoRequest;
import com.guilledev.backend.tipo_de_producto.dto.TipoDeProductoResponse;
import com.guilledev.backend.tipo_de_producto.service.TipoDeProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/tipo-de-producto")
@Tag(name = "Tipo de Producto", description = "Operaciones relacionadas con la gestión de tipos de productos")
public class TipoDeProductoController {

    private final TipoDeProductoService tipoDeProductoService;

    public TipoDeProductoController(TipoDeProductoService tipoDeProductoService) {
        this.tipoDeProductoService = tipoDeProductoService;
    }

    // Crear
    @Operation(summary = "Crear un tipo de producto", description = "Crea un nuevo tipo de producto en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de producto creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Los datos enviados no son válidos"),
            @ApiResponse(responseCode = "409", description = "El tipo de producto ya existe")
    })

    @PostMapping
    public TipoDeProductoResponse guardar(@Valid @RequestBody TipoDeProductoRequest request) {
        return tipoDeProductoService.guardar(request);
    }

    // Actualizar
    @Operation(summary = "Actualizar un tipo de producto", description = "Actualiza los datos de un tipo de producto existente y activo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de producto actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "El tipo de producto no existe")
    })

    @PutMapping("{id}")
    public TipoDeProductoResponse actualizar(@Valid @PathVariable Long id, @RequestBody TipoDeProductoRequest request) {
        return tipoDeProductoService.actualizar(id, request);
    }

    // Listar
    @Operation(summary = "Listar tipos de productos", description = "Obtiene una lista de todos los tipos de productos existentes en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tipos de productos obtenida correctamente")
    })

    @GetMapping
    public List<TipoDeProductoResponse> listar() {
        return tipoDeProductoService.listar();
    }

    // Listar por ID
    @Operation(summary = "Obtener un tipo de producto por ID", description = "Obtiene los detalles de un tipo de producto específico utilizando su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de producto encontrado"),
            @ApiResponse(responseCode = "404", description = "El tipo de producto no existe")
    })

    @GetMapping("{id}")
    public TipoDeProductoResponse buscarPorID(@PathVariable Long id) {
        return tipoDeProductoService.buscarPorID(id);
    }

    // Eliminar estado activo de producto
    @Operation(summary = "Eliminar un tipo de producto", description = "Desactiva un tipo de producto existente mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "El tipo de producto no existe")
    })

    @DeleteMapping("{id}")
    public String eliminar(@PathVariable Long id) {
        tipoDeProductoService.eliminar(id);
        return "Tipo de producto con ID '" + id + "' eliminado correctamente";
    }

    // Activar estado de tipo de producto
    @Operation(summary = "Activar un tipo de producto", description = "Activa un tipo de producto existente mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de producto activado correctamente"),
            @ApiResponse(responseCode = "404", description = "El tipo de producto no existe")
    })

    @PutMapping("{id}/activar")
    public void activar(@PathVariable Long id) {
        tipoDeProductoService.activar(id);
    }

    // Buscar tipos de productos por nombre
    @Operation(summary = "Buscar tipos de productos por nombre", description = "Obtiene un tipo de producto que coinciden con el nombre proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de producto encontrado"),
            @ApiResponse(responseCode = "404", description = "El tipo de producto no existe")
    })
    @GetMapping("/buscarNombres")
    public List<TipoDeProductoResponse> obtenerPorNombre(
            @RequestParam String nombre) {
        return tipoDeProductoService.buscarPorNombre(nombre);
    }

}
