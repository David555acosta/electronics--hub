package com.curso.expecializacion.product.infraestructure.api.mapper;

import com.curso.expecializacion.product.application.command.create.ProductCreateRequest;
import com.curso.expecializacion.product.application.command.update.UpdateProductCreateRequest;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.infraestructure.api.dto.CreateProductDTO;
import com.curso.expecializacion.product.infraestructure.api.dto.ProductDTO;
import com.curso.expecializacion.product.infraestructure.api.dto.UpdateProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {
    ProductCreateRequest mapTocreateProductoRequest(CreateProductDTO createProductDTO);
    
    UpdateProductCreateRequest mapToUpdateProductRequest(UpdateProductDTO updateProductDTO);

    ProductDTO mapToProduct(Product product);

}
