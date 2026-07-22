package com.guilledev.backend.categoria.controller;

import com.guilledev.backend.categoria.service.CategoriaService;
import com.guilledev.backend.categoria.service.CategoriaServiceImpl;
import org.springframework.web.bind.annotation.RestController;

import com.guilledev.backend.categoria.dto.CategoriaRequest;
import com.guilledev.backend.categoria.dto.CategoriaResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/categoria/")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // Crear
    @PostMapping
    public CategoriaResponse guardar(@RequestBody CategoriaRequest request) {
        return categoriaService.guardar(request);
    }

    // Actualizar
    @PutMapping("/{id}")
    public CategoriaResponse actualizar(@PathVariable Long id, @RequestBody CategoriaRequest request) {
        return categoriaService.actualizar(id, request);
    }

    // Listar
    @GetMapping
    public List<CategoriaResponse> listar() {
        return categoriaService.listar();
    }

    // Listar por categoria
    @GetMapping("/{id}")
    public CategoriaResponse buscarPorID(@PathVariable Long id) {
        return categoriaService.buscarPorID(id);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);

        return "Categoría con '" + id + "' eliminada correctamente";
    }

}
