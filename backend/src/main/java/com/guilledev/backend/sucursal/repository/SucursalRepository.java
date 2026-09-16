package com.guilledev.backend.sucursal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilledev.backend.sucursal.entity.Sucursal;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdNot(String nombre, Long id);

    Optional<Sucursal> findByNombre(String nombre);

    List<Sucursal> findByNombreContainingIgnoreCase(String nombre);
}
