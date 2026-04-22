package com.curso.expecializacion.product.application.command.create;


import com.curso.expecializacion.product.common.mediator.RequestHandler;
import com.curso.expecializacion.product.common.util.FileUtilService;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.domain.product_repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductCreateHandler implements RequestHandler<ProductCreateRequest, Void> {

    private final product_repository repository;
    private final FileUtilService fileUtilService;


    @Override
    public Void handle(ProductCreateRequest request) {

        String uniqueFileName = fileUtilService.SaveProduct(request.getFile());

        Product product = Product.builder()
                .codigo(request.getCodigo())
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .imagen(uniqueFileName).build();
        repository.save(product);
        return null;
    }

    @Override
    public Class<ProductCreateRequest> getRequesType() {

        return ProductCreateRequest.class;
    }
}
