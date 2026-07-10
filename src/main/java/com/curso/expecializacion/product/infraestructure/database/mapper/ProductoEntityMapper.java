package com.curso.expecializacion.product.infraestructure.database.mapper;

import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;


import org.mapstruct.*;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductoEntityMapper {
    // De Dominio a Entidad de Base de Datos
    @Mapping(target = "productDetailEntity", source = "productDetail")
    @Mapping(target = "productDetailEntity.product", ignore = true) // Evita ciclo infinito
    ProductEntity mapToProductEntity(Product product);



    // De Entidad de Base de Datos a Dominio
    @Mapping(target = "productDetail", source = "productDetailEntity")
    @Mapping(target = "productDetail.product", ignore = true) // Evita ciclo infinito
    Product mapToProduct(ProductEntity productEntity);
}
