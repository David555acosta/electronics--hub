package com.curso.expecializacion.product.domain;

import java.util.List;
import java.util.Optional;

public interface product_repository {
    void save(Product product);

    Optional<Product> findById(Integer id);

    List<Product> findAll();

    void update(Product product);

    void delete(Integer id);
}
