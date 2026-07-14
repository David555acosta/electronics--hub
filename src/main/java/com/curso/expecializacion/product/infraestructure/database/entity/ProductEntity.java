package com.curso.expecializacion.product.infraestructure.database.entity;

import com.curso.expecializacion.category.infraestructure.CategoryEntity;
import com.curso.expecializacion.producDetail.infraestructure.ProductDetailEntity;
import com.curso.expecializacion.review.infraestructure.ReviewEntity;
import jakarta.persistence.*;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productos")
@Getter
@Setter
@Transactional(rollbackOn =  Exception.class)
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;

    @OneToOne(cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name = "product_details_id")
    @org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
    private ProductDetailEntity productDetailEntity;


    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReviewEntity> reviews = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "productos_categorias",
            joinColumns = @JoinColumn(name = "products_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    Set<CategoryEntity> categoryEntities = new HashSet<>();
}
