package com.curso.expecializacion.product.infraestructure.database.mapper;

import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;


import org.mapstruct.*;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductoEntityMapper {
    @Mapping(target = "productDetailEntity" , source = "productDetail")
    @Mapping(target = "productDetailEntity.productEntity" , ignore = true)
    ProductEntity mapToProductEntity(Product product);



    @Mapping(target = "productDetail" , source = "productDetailEntity.productEntity")
    @Mapping(target = "productDetail.product" , ignore = true)
    Product mapToProduct(ProductEntity productEntity);
}
