package com.curso.expecializacion.category.domain;

import com.curso.expecializacion.product.domain.Product;
import lombok.Builder;
import lombok.Data;
import java.util.Set;

@Data
@Builder
public class Category {
    Integer id;
    String name;
    Set<Product> products;
}
