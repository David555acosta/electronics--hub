package com.curso.expecializacion.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductFilter {
    private String nombre;
    private String descripcion;
    private Double priceMin;
    private Double priceMax;
}
