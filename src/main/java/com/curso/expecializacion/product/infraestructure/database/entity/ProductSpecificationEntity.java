package com.curso.expecializacion.product.infraestructure.database.entity;

import org.springframework.data.jpa.domain.Specification;

public class ProductSpecificationEntity {
    public static Specification<ProductEntity> byName(String name) {
        return (root, query, cb) -> name == null ? null :
                cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<ProductEntity> byDescription(String description) {
        return (root, query, cb) -> description == null ? null :
                cb.like(root.get("name"), "%" + description + "%");
    }

    public static Specification<ProductEntity> byPrecio(Double precioMin , Double precioMax) {
        return (root, query, cb) ->
                precioMin == null || precioMax == null ? null :
                cb.between(root.get("name"), precioMin, precioMax);
    }
}
