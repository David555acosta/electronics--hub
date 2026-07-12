package com.curso.expecializacion.product.domain;

import com.curso.expecializacion.category.infraestructure.CategoryEntity;
import com.curso.expecializacion.producDetail.domain.ProductDetail;
import com.curso.expecializacion.review.infraestructure.ReviewEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Product {
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;

    private ProductDetail productDetail;
    private List<ReviewEntity> reviews;
    private List<CategoryEntity> category;
}
