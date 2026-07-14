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
    // === DOMAIN -> ENTITY ===
    @Mapping(target = "productDetailEntity", source = "productDetail")
    @Mapping(target = "productDetailEntity.product", ignore = true)
    @Mapping(target = "categoryEntities", source = "category")
    // CORRECCIÓN 1: Mapeamos explícitamente reviews
    @Mapping(target = "reviews", source = "reviews")
    ProductEntity mapToProductEntity(Product product);


    // === ENTITY -> DOMAIN ===
    @Mapping(target = "productDetail", source = "productDetailEntity")
    @Mapping(target = "productDetail.product", ignore = true)
    // CORRECCIÓN 2: Eliminamos el 'ignore = true' de las reviews para que se mapeen correctamente de vuelta
    @Mapping(target = "reviews", source = "reviews")
    @Mapping(target = "category", source = "categoryEntities")
    Product mapToProduct(ProductEntity productEntity);


    @Mapping(target = "product", ignore = true)
    Review mapToReview(ReviewEntity reviewEntity);

    @Mapping(target = "productEntity", ignore = true)
    ReviewEntity mapToReviewEntity(Review review);


    @Mapping(target = "products", ignore = true)
    Category mapToCategory(CategoryEntity categoryEntity);

    @Mapping(target = "products", ignore = true)
    CategoryEntity mapToCategoryEntity(Category category);

    @AfterMapping
    default void linkReviews(@MappingTarget ProductEntity productEntity) {
        if (productEntity.getReviews() != null) {
            productEntity.getReviews().forEach(r -> r.setProductEntity(productEntity));
        }
    }

}
