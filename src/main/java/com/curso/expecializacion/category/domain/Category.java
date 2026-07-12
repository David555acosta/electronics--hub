package com.curso.expecializacion.category.domain;

import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Category {
    Integer id;
    String name;
    List<Product> products;
}
