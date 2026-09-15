package com.guilledev.backend.categoria.controller;

import com.guilledev.backend.categoria.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RestController;

import com.guilledev.backend.categoria.dto.CategoriaRequest;
import com.guilledev.backend.categoria.dto.CategoriaResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/categoria")
@Tag(name = "Categoría", description = "Operaciones relacionadas con la gestión de categorías")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // Crear
    @Operation(summary = "Crear una categoría", description = "Crea una nueva categoría en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Los datos enviados no son válidos"),
            @ApiResponse(responseCode = "409", description = "La categoría ya existe")
    })

    @PostMapping
    public CategoriaResponse guardar(@Valid @RequestBody CategoriaRequest request) {
        return categoriaService.guardar(request);
    }

    // Actualizar
    @Operation(summary = "Actualizar una categoría", description = "Actualiza los datos de una categoría existente y activa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "La categoría no existe")
    })
    @PutMapping("{id}")
    public CategoriaResponse actualizar(@Valid @PathVariable Long id, @RequestBody CategoriaRequest request) {
        return categoriaService.actualizar(id, request);
    }

    // Listar
    @Operation(summary = "Listar categorías", description = "Obtiene todas las categorías registradas")
    @ApiResponse(responseCode = "200", description = "Categorías obtenidas correctamente")
    @GetMapping
    public List<CategoriaResponse> listar() {
        return categoriaService.listar();
    }

    // Listar por Id
    @Operation(summary = "Buscar categoría por Id", description = "Obtiene una categoría específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
            @ApiResponse(responseCode = "404", description = "La categoría no existe")
    })
    @GetMapping("{id}")
    public CategoriaResponse buscarPorID(@PathVariable Long id) {
        return categoriaService.buscarPorID(id);
    }

    // Eliminar
    @Operation(summary = "Eliminar una categoría", description = "Desactiva una categoría existente mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "La categoría no existe")
    })
    @DeleteMapping("{id}")
    public String eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);

        return "Categoría con '" + id + "' eliminada correctamente";
    }

    // Activar estado de la categoría
    @Operation(summary = "Activar una categoría", description = "Activa una categoría existente mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría activada correctamente"),
            @ApiResponse(responseCode = "404", description = "La categoría no existe")
    })
    @PutMapping("{id}/activar")
    public void activar(@PathVariable Long id) {
        categoriaService.activar(id);
    }

    // Buscar por nombre
    @Operation(summary = "Buscar categorías por nombres", description = "Obtiene una categoría específica por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
            @ApiResponse(responseCode = "404", description = "La categoría no existe")
    })
    @GetMapping("/buscarNombres")
    public List<CategoriaResponse> obtenerPorNombre(
            @RequestParam String nombre) {
        return categoriaService.buscarPorNombre(nombre);
    }

}
