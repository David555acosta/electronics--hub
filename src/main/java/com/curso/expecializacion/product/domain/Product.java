package com.curso.expecializacion.product.domain;

import com.curso.expecializacion.category.domain.Category;
import com.curso.expecializacion.producDetail.domain.ProductDetail;
import com.curso.expecializacion.review.domain.Review;
import lombok.Builder;
import lombok.Data;
import java.util.Set;

@Data
@Builder
public class Product {
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;

    private ProductDetail productDetail;

    private Set<Review> reviews;
    private Set<Category> category;
}
