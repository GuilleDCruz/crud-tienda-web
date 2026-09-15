package com.guilledev.backend.tipo_de_producto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilledev.backend.tipo_de_producto.entity.TipoDeProducto;

@Repository
public interface TipoDeProductoRepository extends JpaRepository<TipoDeProducto, Long> {

    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdNot(String nombre, Long id);

    Optional<TipoDeProducto> findByNombre(String nombre);

    List<TipoDeProducto> findAllByActivoTrue();

    List<TipoDeProducto> findByNombreContainingIgnoreCase(String nombre);

}
