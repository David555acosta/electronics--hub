package com.curso.expecializacion.product.application.query.getById;

import com.curso.expecializacion.product.common.mediator.RequestHandler;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.domain.ProductNotFoundException;
import com.curso.expecializacion.product.domain.product_repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetProductByIdHandler implements RequestHandler<GetProductByIdRequest, GetProductByIdResponse> {

    private final product_repository repository;


    @Override
    public GetProductByIdResponse handle(GetProductByIdRequest request) {
        log.info("OBTENIENDO POR ID , PRODUCT BYID HANDLER , CODIGO:{}");
        Product product = repository.findById(request.getCodigo()).orElseThrow(() -> new ProductNotFoundException(request.getCodigo()));

        log.info("TODOS OBTENIDO POR ID , PRODUCT BYID HANDLER , Codigo:{}", product.getCodigo());
        return new GetProductByIdResponse(product);
    }

    @Override
    public Class<GetProductByIdRequest> getRequesType() {
        return GetProductByIdRequest.class;
    }
}
