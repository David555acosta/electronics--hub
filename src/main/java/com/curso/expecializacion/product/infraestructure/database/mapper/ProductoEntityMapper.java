package com.curso.expecializacion.product.infraestructure.database.mapper;

import com.curso.expecializacion.category.domain.Category;
import com.curso.expecializacion.category.infraestructure.CategoryEntity;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;


import com.curso.expecializacion.review.domain.Review;
import com.curso.expecializacion.review.infraestructure.ReviewEntity;
import org.mapstruct.*;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductoEntityMapper {
    @Mapping(target = "productDetailEntity", source = "productDetail")
    @Mapping(target = "productDetailEntity.product", ignore = true)
    @Mapping(target = "categoryEntities", source = "category")
    ProductEntity mapToProductEntity(Product product);


    @Mapping(target = "productDetail", source = "productDetailEntity")
    @Mapping(target = "productDetail.product", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "category", source = "categoryEntities")
    Product mapToProduct(ProductEntity productEntity);


    @Mapping(target = "product", ignore = true)
    Review mapToReviewDTO(ReviewEntity reviewEntity);

    @Mapping(target = "productEntity", ignore = true)
    ReviewEntity mapToReviewEntity(Review review);


    @Mapping(target = "products", ignore = true)
    Category mapToCategory(CategoryEntity categoryEntity);

    @Mapping(target = "products", ignore = true)
    CategoryEntity mapToCategoryEntity(Category category);
}
