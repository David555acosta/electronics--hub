package com.curso.expecializacion.product.infraestructure.database.entity;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ProductEntity {
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;
}
