package com.curso.expecializacion.product.infraestructure.database;

import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;
import com.curso.expecializacion.product.infraestructure.database.repositoryDBProducts.QueryProductsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
class ProductoRepositoryJPATest {

    @Autowired
    private QueryProductsRepository queryProductsRepository;

    @Test
    void shouldNotReturnProductWhenNotFound() {

        Optional<ProductEntity> optionalProduct = queryProductsRepository.findById(1);
        assertTrue(optionalProduct.isEmpty());
    }

    @Test
    void shouldReturnProductWhenFound() {

        ProductEntity productEntity = new ProductEntity();
        ProductEntity save = queryProductsRepository.save(productEntity);

        Optional<ProductEntity> optionalProduct = queryProductsRepository.findById(save.getCodigo());
        assertTrue(optionalProduct.isPresent());
    }
}