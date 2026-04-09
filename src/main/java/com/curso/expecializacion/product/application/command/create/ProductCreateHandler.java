package com.curso.expecializacion.product.application.command.create;

import com.curso.expecializacion.product.commongMediator.RequestHandler;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.domain.product_repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCreateHandler implements RequestHandler<ProductCreateRequest, Void> {

    private final product_repository repository;

    @Override
    public Void handle(ProductCreateRequest request) {
        Product product = Product.builder()
                .codigo(1)
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .imagen(request.getImagen()).build();
        repository.save(product);
        return null;
    }

    @Override
    public Class<ProductCreateRequest> getRequesType() {
        return null;
    }
}
