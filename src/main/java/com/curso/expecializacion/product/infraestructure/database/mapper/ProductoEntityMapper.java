package com.curso.expecializacion.product.infraestructure.database.mapper;

import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;


import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductoEntityMapper {
    ProductEntity mapToProductEntity(Product product);

    Product mapToProduct(ProductEntity productEntity);
}
