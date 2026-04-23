package com.curso.expecializacion.product.application.command.create;


import com.curso.expecializacion.product.common.mediator.RequestHandler;
import com.curso.expecializacion.product.common.util.FileUtilService;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.domain.product_repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCreateHandler implements RequestHandler<ProductCreateRequest, Void> {

    private final product_repository repository;
    private final FileUtilService fileUtilService;


    @Override
    public Void handle(ProductCreateRequest request) {

        log.info("Creando producto , PRODUCT CREATE HANDLER , Codigo:{}", request.getCodigo());

        String uniqueFileName = fileUtilService.SaveProduct(request.getFile());

        Product product = Product.builder()
                .codigo(request.getCodigo())
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .imagen(uniqueFileName).build();
        repository.save(product);

        log.info("Producto CREADO , PRODUCT CREATE HANDLER , Codigo:{}", request.getCodigo());
        return null;
    }

    @Override
    public Class<ProductCreateRequest> getRequesType() {

        return ProductCreateRequest.class;
    }
}
