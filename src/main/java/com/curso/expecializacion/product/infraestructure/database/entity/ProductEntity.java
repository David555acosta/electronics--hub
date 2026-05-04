package com.curso.expecializacion.product.infraestructure.database.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "productos")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;
}
