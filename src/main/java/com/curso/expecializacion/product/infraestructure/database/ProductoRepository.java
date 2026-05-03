package com.curso.expecializacion.product.infraestructure.database;

import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.domain.product_repository;
import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;
import com.curso.expecializacion.product.infraestructure.database.mapper.ProductoEntityMapper;


import com.curso.expecializacion.product.infraestructure.database.repositoryDBProducts.QueryProductsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductoRepository implements product_repository {


    private final ProductoEntityMapper productoEntityMapper;

    private final QueryProductsRepository repository;


    @Override
    public Product save(Product product) {
        log.info("Guardando producto REPOSITORY");
        ProductEntity productEntity = productoEntityMapper.mapToProductEntity(product);
        ProductEntity guardado = repository.save(productEntity);
        return productoEntityMapper.mapToProduct(guardado);
    }

    @Cacheable(value = "products", key = "#id")
    @Override
    public Optional<Product> findById(Integer id) {
        log.info("Cacheable , PRODUCT FIND HANDLER REPOSITORIO , Codigo:{}", id);
        return repository.findById(id).map(productoEntityMapper::mapToProduct);
    }

    @Override
    public List<Product> findAll() {
        log.info("OBTENIENDO TODOS REPOSITORIO");
        return repository.findAll().stream().map(productoEntityMapper::mapToProduct).toList();
    }

    @Override
    public Product update(Product product) {
        return product;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
