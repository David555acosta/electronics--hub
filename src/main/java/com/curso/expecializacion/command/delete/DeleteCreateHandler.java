package com.curso.expecializacion.command.delete;


import com.curso.expecializacion.product.common.mediator.RequestHandler;
import com.curso.expecializacion.product.domain.product_repository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteCreateHandler implements RequestHandler<DeleteProductRequest, Void> {

    private final product_repository repository;

    @Override
    @Transactional
    public Void handle(DeleteProductRequest request) {
        log.info("Eliminando producto , PRODUCT DELETE HANDLER , Codigo:{}", request.getCodigo());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        }

        repository.delete(request.getCodigo());

        log.info("producto ELIMINADO , PRODUCT DELETE HANDLER , Codigo:{}", request.getCodigo());
        return null;
    }

    @Override
    public Class<DeleteProductRequest> getRequesType() {

        return DeleteProductRequest.class;
    }
}
