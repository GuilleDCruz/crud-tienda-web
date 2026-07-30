package com.guilledev.backend.marca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilledev.backend.marca.entity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long>{
    boolean existsByNombre(String nombre);
    boolean existsByNombreAndIdNot(String nombre, Long id);
    Optional<Marca> findByNombre(String nombre);
    Optional<Marca> findByNombreIgnoreCase(String nombre);
    
}
