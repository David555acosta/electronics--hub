package com.curso.expecializacion.command.create;


import com.curso.expecializacion.product.common.mediator.RequestHandler;
import com.curso.expecializacion.product.common.util.FileUtilService;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.domain.product_repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Slf4j
@Service
public class ProductCreateHandler implements RequestHandler<ProductCreateRequest, ProductCreateResponse> {

    private final product_repository repository;
    private final FileUtilService fileUtilService;


    @Override
    public ProductCreateResponse handle(ProductCreateRequest request) {

        log.info("Creando producto , PRODUCT CREATE HANDLER");

        String uniqueFileName = fileUtilService.SaveProduct(request.getFile());

        Product product = Product.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .imagen(uniqueFileName).build();

        Product storageProduct = repository.save(product);
        log.info("Producto CREADO , PRODUCT CREATE HANDLER , Codigo:{}", storageProduct.getCodigo());
        return new ProductCreateResponse(storageProduct);
    }

    @Override
    public Class<ProductCreateRequest> getRequesType() {

        return ProductCreateRequest.class;
    }
}
