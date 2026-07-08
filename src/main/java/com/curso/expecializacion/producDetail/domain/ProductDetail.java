package com.curso.expecializacion.producDetail.domain;

import com.curso.expecializacion.product.domain.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetail {
    private Integer id;
    private String specifications;
    private String warranty;
    private String provider;

    private Product product;

}
