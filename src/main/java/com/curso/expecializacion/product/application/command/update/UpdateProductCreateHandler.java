package com.curso.expecializacion.product.application.command.update;


import com.curso.expecializacion.product.application.command.create.ProductCreateRequest;
import com.curso.expecializacion.product.commongMediator.RequestHandler;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.domain.product_repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProductCreateHandler implements RequestHandler<UpdateProductCreateRequest, Void> {

    private final product_repository repository;

    @Override
    public Void handle(UpdateProductCreateRequest request) {
        Product product = Product.builder()
                .codigo(request.getCodigo())
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .imagen(request.getImagen()).build();
        repository.save(product);
        return null;
    }

    @Override
    public Class<UpdateProductCreateRequest> getRequesType() {

        return UpdateProductCreateRequest.class;
    }
}
