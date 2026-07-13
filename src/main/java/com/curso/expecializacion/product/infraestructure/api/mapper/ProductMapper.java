package com.curso.expecializacion.product.infraestructure.api.mapper;

import com.curso.expecializacion.category.domain.Category;
import com.curso.expecializacion.category.infraestructure.CategoryEntity;
import com.curso.expecializacion.product.application.command.create.ProductCreateRequest;
import com.curso.expecializacion.product.application.command.update.UpdateProductCreateRequest;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.infraestructure.api.dto.CreateProductDTO;
import com.curso.expecializacion.product.infraestructure.api.dto.ProductDTO;
import com.curso.expecializacion.product.infraestructure.api.dto.ReviewDTO;
import com.curso.expecializacion.product.infraestructure.api.dto.UpdateProductDTO;
import com.curso.expecializacion.review.domain.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {
    ProductCreateRequest mapTocreateProductoRequest(CreateProductDTO createProductDTO);

    UpdateProductCreateRequest mapToUpdateProductRequest(UpdateProductDTO updateProductDTO);

    @Mapping(target = "provider" , source = "productDetail.provider")
    @Mapping(target = "categories", source = "category")
    ProductDTO mapToProductDto(Product product);

    @Mapping(target = "product" , ignore = true)
    Review mapToReview(ReviewDTO reviewDTO);

    default List<String> mapToCategoryName(List<Category> categories) {

        return  categories.stream().map(Category::getName).toList();
    }

}
