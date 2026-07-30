package com.guilledev.backend.marca.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;
    
@Entity
@Table(name="marca")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Marca{ 
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(
    nullable = false,
    unique = true,
    length = 100
    )
    private String nombre;

    @Column(length = 100)
    private String descripcion;

    @Column(nullable = false)
    private Boolean activo;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}