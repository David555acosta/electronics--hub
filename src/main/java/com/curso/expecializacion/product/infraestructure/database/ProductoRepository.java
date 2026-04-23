package com.curso.expecializacion.product.infraestructure.database;

import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.domain.product_repository;
import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;
import com.curso.expecializacion.product.infraestructure.database.mapper.ProductoEntityMapper;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class ProductoRepository implements product_repository {

    public ProductoRepository(ProductoEntityMapper productoEntityMapper) {
        this.productoEntityMapper = productoEntityMapper;
    }


    private final List<ProductEntity> productList = new ArrayList<>();


    private final ProductoEntityMapper productoEntityMapper;


    @Override
    public void save(Product product) {
        ProductEntity productEntity = productoEntityMapper.mapToProductEntity(product);
        productList.removeIf(p -> p.getCodigo().equals(productEntity.getCodigo()));
        productList.add(productEntity);
    }

    @Cacheable(value = "products", key = "#id")
    @Override
    public Optional<Product> findById(Integer id) {
        log.info("Cacheable , PRODUCT FIND HANDLER , Codigo:{}", id);
        return productList.stream().filter(p -> p.getCodigo().equals(id)).findFirst().map(productoEntityMapper::mapToProduct);
    }

    @Override
    public List<Product> findAll() {
        return productList.stream().map(productoEntityMapper::mapToProduct).toList();
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Integer id) {

        productList.removeIf(p -> p.getCodigo().equals(id));
    }
}
