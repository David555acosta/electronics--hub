package com.curso.expecializacion.product.application.command.delete;


import com.curso.expecializacion.product.common.mediator.RequestHandler;
import com.curso.expecializacion.product.domain.product_repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCreateHandler implements RequestHandler<DeleteProductRequest, Void> {

    private final product_repository repository;

    @Override
    public Void handle(DeleteProductRequest request) {
        repository.delete(request.getCodigo());
        return null;
    }

    @Override
    public Class<DeleteProductRequest> getRequesType() {

        return DeleteProductRequest.class;
    }
}
