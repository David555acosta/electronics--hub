package com.curso.expecializacion.product.application.query.getAll;

import com.curso.expecializacion.config.domain.PaginationResult;
import com.curso.expecializacion.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data

public class AllGetProductResponse {

    private PaginationResult<Product> productsPage;
}


