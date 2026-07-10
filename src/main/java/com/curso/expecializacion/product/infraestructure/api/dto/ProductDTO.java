package com.curso.expecializacion.product.infraestructure.api.dto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ProductDTO {
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;
    private String provider;
    private List<ReviewDTO> reviews;
}
