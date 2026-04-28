package com.curso.expecializacion.product.application.query.getAll;

import com.curso.expecializacion.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@AllArgsConstructor
@Data

public class AllGetProductResponse {

    private List<Product> product;
}


