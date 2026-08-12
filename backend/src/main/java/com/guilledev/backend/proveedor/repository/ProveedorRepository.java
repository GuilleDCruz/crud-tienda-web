package com.guilledev.backend.proveedor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilledev.backend.proveedor.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdNot(String nombre, Long id);

    Optional<Proveedor> findByNombre(String nombre);

    List<Proveedor> findByNombreContainingIgnoreCase(String nombre);
}
