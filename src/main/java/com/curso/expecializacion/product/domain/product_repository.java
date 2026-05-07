package com.curso.expecializacion.product.domain;

import com.curso.expecializacion.product.common.domain.PaginationQuery;
import com.curso.expecializacion.product.common.domain.PaginationResult;

import java.util.List;
import java.util.Optional;

public interface product_repository {
    Product save(Product product);

    Optional<Product> findById(Integer id);

    PaginationResult<Product> findAll(PaginationQuery paginationQuery );

    Product update(Product product);

    void delete(Integer id);
}
