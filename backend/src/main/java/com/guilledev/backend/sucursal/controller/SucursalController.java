package com.guilledev.backend.sucursal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guilledev.backend.sucursal.dto.SucursalRequest;
import com.guilledev.backend.sucursal.dto.SucursalResponse;
import com.guilledev.backend.sucursal.service.SucursalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sucursal")
@Tag(name = "Sucursal", description = "Operaciones relacionadas con la gestion de sucursales")
public class SucursalController {
    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    // Crear
    @Operation(summary = "Crear una sucursal", description = "Crea una sucursal en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Los datos enviados no son validos")
    })

    @PostMapping
    public SucursalResponse guardar(@Valid @RequestBody SucursalRequest request) {
        return sucursalService.guardar(request);
    }

    // Actualizar
    @Operation(summary = "Actualiza una sucursal", description = "Actualiza una sucursal en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "La sucursal no existe")
    })

    @PutMapping("{id}")
    public SucursalResponse actualizar(@Valid @PathVariable Long id, @RequestBody SucursalRequest request) {
        return sucursalService.actualizar(id, request);
    }

    // Listar
    @Operation(summary = "Lista todas las sucursales", description = "Lista todas las sucursales en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursales listadas correctamente"),
    })

    @GetMapping
    public List<SucursalResponse> listar() {
        return sucursalService.listar();
    }

    // Listar por ID
    @Operation(summary = "Lista una sucursal por ID", description = "Lista una sucursal por ID en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal encontrada correctamente"),
            @ApiResponse(responseCode = "404", description = "La sucursal no existe")
    })

    @GetMapping("{id}")
    public SucursalResponse buscarPorID(@PathVariable Long id) {
        return sucursalService.buscarPorID(id);
    }

    // Desactivar
    @Operation(summary = "Desactiva una sucursal", description = "Desactiva una sucursal en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal desactivada correctamente"),
            @ApiResponse(responseCode = "404", description = "La sucursal no existe")
    })

    @DeleteMapping("{id}")
    public String eliminar(@PathVariable Long id) {
        sucursalService.eliminar(id);
        return "Sucursal con " + id + " desactivada correctamente";
    }

    // Activar
    @Operation(summary = "Activa una sucursal", description = "Activa una sucursal en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal activada correctamente"),
            @ApiResponse(responseCode = "404", description = "La sucursal no existe")
    })

    @PutMapping("{id}/activar")
    public void activar(@PathVariable Long id) {
        sucursalService.activar(id);
    }

    // Buscar por nombre
    @Operation(summary = "Busca sucursales por nombre", description = "Busca sucursales por nombre en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursales encontradas correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron sucursales")
    })

    @GetMapping("/buscarNombres")
    public List<SucursalResponse> buscarPorNombre(@RequestParam String nombre) {
        return sucursalService.buscarPorNombres(nombre);
    }

}
