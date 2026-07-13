package com.curso.expecializacion.producDetail.infraestructure;

import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ProductEntity product;
}
