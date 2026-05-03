package com.curso.expecializacion.product.domain;

import java.util.List;
import java.util.Optional;

public interface product_repository {
    Product save(Product product);

    Optional<Product> findById(Integer id);

    List<Product> findAll();

    Product update(Product product);

    void delete(Integer id);
}
