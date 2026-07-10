package com.curso.expecializacion.review.domain;

import com.curso.expecializacion.product.domain.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {
    private Integer id;
    private String comment;
    private Integer source;


    private Product product;

}
