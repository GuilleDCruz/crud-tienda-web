package com.guilledev.backend.categoria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilledev.backend.categoria.entity.Categoria;

public interface CategoriaRepository
        extends JpaRepository<Categoria, Long> {
    boolean existsByNombre(String nombre);
}
