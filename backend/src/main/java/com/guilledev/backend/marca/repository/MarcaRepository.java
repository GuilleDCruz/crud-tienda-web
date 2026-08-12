package com.guilledev.backend.marca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilledev.backend.marca.entity.Marca;
import com.guilledev.backend.proveedor.entity.Proveedor;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdNot(String nombre, Long id);

    Optional<Marca> findByNombre(String nombre);

    List<Marca> findByNombreContainingIgnoreCase(String nombre);

}
