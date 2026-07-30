package com.guilledev.backend.marca.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guilledev.backend.marca.dto.MarcaRequest;
import com.guilledev.backend.marca.dto.MarcaResponse;
import com.guilledev.backend.marca.service.MarcaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/marca")
@Tag(name = "Marca", description = "Operaciones relacoadas con la gestión de marcas")
public class MarcaController {
    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    // Crear
    @Operation(summary = "Crear una marca", description = "Crea una nueva marca en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Los datos enviados no son válidos"),
            @ApiResponse(responseCode = "409", description = "La marca ya existe")
    })

    @PostMapping
    public MarcaResponse guardar(@Valid @RequestBody MarcaRequest request) {
        return marcaService.guardar(request);
    }

    // Actualizar
    @Operation(summary = "Actualizar una marca", description = "Actualiza los datos de una marca existente y activa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "La marca no existe")
    })
    @PutMapping("{id}")
    public MarcaResponse actualizar(@Valid @PathVariable Long id, @RequestBody MarcaRequest request) {
        return marcaService.actualizar(id, request);
    }

    // Listar
    @Operation(summary = "Listar marcas", description = "Obtiene todas las marcas registradas")
    @ApiResponse(responseCode = "200", description = "Marcas obtenidas correctamente")
    @GetMapping
    public List<MarcaResponse> listar() {
        return marcaService.listar();
    }

    // Listar por Id
    @Operation(summary = "Buscar marca por Id", description = "Obtiene una marca específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca encontrada"),
            @ApiResponse(responseCode = "404", description = "La marca no existe")
    })
    @GetMapping("{id}")
    public MarcaResponse buscarPorID(@PathVariable Long id) {
        return marcaService.buscarPorID(id);
    }

    // Eliminar
    @Operation(summary = "Eliminar una marca", description = "Desactiva una marca existente mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "La marca no existe")
    })
    @DeleteMapping("{id}")
    public String eliminar(@PathVariable Long id) {
        marcaService.eliminar(id);
        return "marca con '" + id + "' eliminada correctamente";
    }

    // Actuivar estado de la marca
    @Operation(summary = "Activar una marca", description = "Activa una marca existente mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca activada correctamente"),
            @ApiResponse(responseCode = "404", description = "La marca no existe")
    })

    @PutMapping("{id}/activar")
    public void activar(@PathVariable Long id) {
        marcaService.activar(id);
    }

    // Buscar por nombre
    @Operation(summary = "Buscar marca por nombre", description = "Obtiene una marca específica por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca encontrada"),
            @ApiResponse(responseCode = "404", description = "La marca no existe")
    })
    @GetMapping("/buscar")
    public MarcaResponse obtenerPorNombre(
            @RequestParam String nombre) {
        return marcaService.buscarPorNombre(nombre);
    }

}