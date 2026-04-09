package com.curso.expecializacion.product.application.query.getbyid;

import com.curso.expecializacion.product.commongMediator.RequestHandler;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.domain.product_repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProductByIdHandler implements RequestHandler<GetProductByIdRequest, GetProductByIdResponse> {

    private final product_repository repository;


    @Override
    public GetProductByIdResponse handle(GetProductByIdRequest request) {
        Product product = repository.findById(request.getCodigo()).orElseThrow(() -> new RuntimeException("No existe el producto con el id: " + request.getCodigo()));

        return new GetProductByIdResponse(product);
    }

    @Override
    public Class<GetProductByIdRequest> getRequesType() {
        return GetProductByIdRequest.class;
    }
}
