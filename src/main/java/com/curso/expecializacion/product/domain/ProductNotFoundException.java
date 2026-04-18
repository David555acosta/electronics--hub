package com.curso.expecializacion.product.domain;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer id) {
        super(id + " no encontrado");
    }

}
