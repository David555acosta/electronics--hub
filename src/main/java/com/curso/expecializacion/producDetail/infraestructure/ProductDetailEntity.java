package com.curso.expecializacion.producDetail.infraestructure;

import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_details")
public class ProductDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String specifications;
    private String warranty;
    private String provider;

    @OneToOne(mappedBy = "productDetailEntity")
    private ProductEntity product;
}
