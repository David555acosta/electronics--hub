package com.curso.expecializacion.product.infraestructure.database.entity;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

@Slf4j
public class ProductSpecificationEntity {

    public static Specification<ProductEntity> byName(String nombre) {
        log.info("EVALUANDO NAME");
        return (root, query, cb) -> nombre == null ? null :
                cb.like(root.get("nombre"), "%" + nombre + "%");
    }

    public static Specification<ProductEntity> byDescription(String descripcion) {
        log.info("EVALUANDO DESCRIPCION");
        return (root, query, cb) -> descripcion == null ? null :
                cb.like(root.get("descripcion"), "%" + descripcion + "%");
    }

    public static Specification<ProductEntity> byPrecio(Double precioMin , Double precioMax) {
        log.info("EVALUANDO PRECIO");
        return (root, query, cb) ->
                precioMin == null || precioMax == null ? null :
                cb.between(root.get("precio"), precioMin, precioMax);
    }
}
