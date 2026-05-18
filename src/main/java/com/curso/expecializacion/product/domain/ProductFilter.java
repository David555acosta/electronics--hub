package com.curso.expecializacion.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductFilter {
    private String name;
    private String description;
    private String priceMin;
    private String priceMax;
}
