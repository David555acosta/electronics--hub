package com.curso.expecializacion.product.infraestructure.database;

import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.domain.product_repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductoRepository implements product_repository {

    private List<Product> productList;

    public ProductoRepository() {
        this.productList = new ArrayList<>();
    }

    @Override
    public void save(Product product) {
        this.productList.add(product);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return  productList.stream().filter(p -> p.getCodigo().equals(id)).findFirst();
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Integer id) {
        productList.remove(findById(id));
    }
}
