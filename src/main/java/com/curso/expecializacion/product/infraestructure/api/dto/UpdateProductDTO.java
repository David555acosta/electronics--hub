package com.curso.expecializacion.product.infraestructure.api.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UpdateProductDTO {
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String provider;
    private Integer categoryId;
}
