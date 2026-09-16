package com.guilledev.backend.sucursal.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sucursal")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Sucursal {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;
    @Column(length = 50)
    private String codigo;
    @Column(length = 250)
    private String direccion;
    @Column(length = 20)
    private String telefono;
    @Column(length = 100)
    private String email;
    @Column(nullable = false)
    private Boolean activo;
    private LocalDateTime createdAdt;
    private LocalDateTime updatedAt;

}
