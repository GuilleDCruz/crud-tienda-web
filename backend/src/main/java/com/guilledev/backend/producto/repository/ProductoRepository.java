package com.guilledev.backend.producto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilledev.backend.producto.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    boolean existsByNombreIgnoreCase(String nombre);
    
    Optional<Producto> findByNombreIgnoreCase(String nombre);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    List<Producto> findByActivoTrue();

}
