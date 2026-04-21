package com.curso.expecializacion.product.application.command.create;


import com.curso.expecializacion.product.common.mediator.RequestHandler;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.domain.product_repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductCreateHandler implements RequestHandler<ProductCreateRequest, Void> {

    private final product_repository repository;

    @Override
    public Void handle(ProductCreateRequest request) {
        MultipartFile file = request.getFile();

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        String uniqueFileName = UUID.randomUUID().toString().concat("").concat(fileName);

        Path path = Path.of("Uploads/Product/");

        try {

        } catch (Exception e) {

        }
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
    public Class<ProductCreateRequest> getRequesType() {

        return ProductCreateRequest.class;
    }
}
