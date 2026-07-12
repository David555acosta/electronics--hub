package com.curso.expecializacion.product.infraestructure.database.entity;

import com.curso.expecializacion.category.infraestructure.CategoryEntity;
import com.curso.expecializacion.producDetail.infraestructure.ProductDetailEntity;
import com.curso.expecializacion.review.infraestructure.ReviewEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_details_id")
    private ProductDetailEntity productDetailEntity;


    @OneToMany(mappedBy = "productEntity")
    private List<ReviewEntity> reviews = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "productos_categorias",
            joinColumns = @JoinColumn(name = "products_id"),       //  CORREGIDO
            inverseJoinColumns = @JoinColumn(name = "category_id") //  CORREGIDO
    )
    List<CategoryEntity> categoryEntities = new ArrayList<>();
}
