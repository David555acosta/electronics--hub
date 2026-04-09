package com.curso.expecializacion.product.infraestructure.database.entity;

import lombok.Getter;

@Getter
public class ProductEntity {
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;
}
