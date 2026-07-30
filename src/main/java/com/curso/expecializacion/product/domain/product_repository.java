package com.curso.expecializacion.product.domain;

import com.curso.expecializacion.config.domain.PaginationQuery;
import com.curso.expecializacion.config.domain.PaginationResult;


import java.util.Optional;

public interface product_repository {
    Product save(Product product);

    Optional<Product> findById(Integer id);

    PaginationResult<Product> findAll(PaginationQuery paginationQuery, ProductFilter productFilter);

    Product update(Product product);

    Optional<Product> delete(Integer id);
}
