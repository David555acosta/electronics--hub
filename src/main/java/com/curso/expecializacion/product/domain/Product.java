package com.curso.expecializacion.product.domain;

import com.curso.expecializacion.producDetail.domain.ProductDetail;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;
    private ProductDetail productDetail;
}
