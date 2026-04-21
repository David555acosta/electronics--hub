package com.curso.expecializacion.product.application.query.getAll;

import com.curso.expecializacion.product.common.mediator.RequestHandler;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.domain.product_repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AllGetProductHandler implements RequestHandler<AllGetProductRequest, AllGetProductResponse> {

    private final product_repository repository;


    @Override
    public AllGetProductResponse handle(AllGetProductRequest request) {
        List<Product> product = repository.findAll();

        return new AllGetProductResponse(product);
    }

    @Override
    public Class<AllGetProductRequest> getRequesType() {
        return AllGetProductRequest.class;
    }
}
