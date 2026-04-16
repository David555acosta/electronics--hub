package com.curso.expecializacion.product.application.query.getAll;

import com.curso.expecializacion.product.application.query.getById.GetProductByIdRequest;
import com.curso.expecializacion.product.application.query.getById.GetProductByIdResponse;
import com.curso.expecializacion.product.commongMediator.RequestHandler;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.domain.product_repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class allGetProductHandler implements RequestHandler<AllGetProductRequest, allGetProductResponse> {

    private final product_repository repository;


    @Override
    public allGetProductResponse handle(AllGetProductRequest request) {
        List<Product> product = repository.findAll();

        return new allGetProductResponse(product);
    }

    @Override
    public Class<AllGetProductRequest> getRequesType() {
        return AllGetProductRequest.class;
    }
}
