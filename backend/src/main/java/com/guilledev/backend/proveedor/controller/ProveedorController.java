package com.guilledev.backend.proveedor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guilledev.backend.proveedor.dto.ProveedorRequest;
import com.guilledev.backend.proveedor.dto.ProveedorResponse;
import com.guilledev.backend.proveedor.service.ProveedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/proveedor")
@Tag(name = "Proveedor", description = "Operaciones relacionadas con la gestion de proveedores")
public class ProveedorController {
    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    // Crear
    @Operation(summary = "Crear un proveedor", description = "Crea un proveedor en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Los datos enviados no son validos"),
            @ApiResponse(responseCode = "409", description = "El proveedor ya existe")
    })

    @PostMapping
    public ProveedorResponse guardar(@Valid @RequestBody ProveedorRequest request) {
        return proveedorService.guardar(request);
    }

    // Acualizar
    @Operation(summary = "Actualiza un proveedor", description = "Actualiza un proveedor en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "El proveedor no existe")
    })

    @PutMapping("{id}")
    public ProveedorResponse actualizar(@Valid @PathVariable Long id, @RequestBody ProveedorRequest request){
        return proveedorService.actualizar(id, request);
    }

    // Listar
    @Operation(summary = "Listar un proveedor", description = "Obtiene todos los proveedores del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedores optenidos correctamente")
    })

    @GetMapping
    public List<ProveedorResponse> listar(){
        return proveedorService.listar();
    }


    // Listar por id
    @Operation(summary = "Listar un proveedor por Id", description = "Obtiene un proveedor especifico por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor encontrado"),
            @ApiResponse(responseCode = "404", description = "El proveedor no existe")
    })

    @GetMapping("{id}")
    public ProveedorResponse buscarPorID(@PathVariable Long id){
        return proveedorService.buscarPorID(id);
    }

    // Desactivar estado del proveedor
    @Operation(summary = "Desactivar un proveedor", description = "Desactiva un proveedor existente por medio del id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor desactivado correctamente"),
            @ApiResponse(responseCode = "404", description = "El proveedor no existe")
    })

    @DeleteMapping("{id}")
    public String eliminar(@PathVariable Long id){
        proveedorService.eliminar(id);
        return "Proveedor con '" + id + "'' desactivado correctamente";
    }

    // Activar estado del proveedor
    @Operation(summary = "Activa un proveedor", description = "Activa un proveedor existente por medio del id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor activado correctamente"),
            @ApiResponse(responseCode = "404", description = "El proveedor no existe")
    })

    @DeleteMapping("{id}/activar")
    public String activar(@PathVariable Long id){
        proveedorService.activar(id);
        return "Proveedor con '" + id + "'' activado correctamente";
    }

    // Buscar por nombre
    @Operation(summary = "Busca proveedor por nombre", description = "Obtiene un proveedor especifico por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor encontrado"),
            @ApiResponse(responseCode = "404", description = "El proveedor no existe")
    })

    @GetMapping("/buscar")
    public ProveedorResponse obtenerPorNombre(@RequestParam String nombre){
        return proveedorService.buscarPorNombre(nombre);
    } 
    

}
