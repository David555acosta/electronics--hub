package com.curso.expecializacion.product.application.command.create;


import com.curso.expecializacion.product.common.mediator.RequestHandler;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.domain.product_repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductCreateHandler implements RequestHandler<ProductCreateRequest, Void> {

    private final product_repository repository;

    String uniqueFileName;

    @Override
    public Void handle(ProductCreateRequest request) {

        MultipartFile file = request.getFile();


        try (InputStream inputStream = file.getInputStream()) {

            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            uniqueFileName = UUID.randomUUID().toString().concat("_").concat(fileName);

            Path path = Path.of("src/main/resources/products");

            if (Files.exists(path)) {

                Files.createDirectories(path);
            }

            Files.copy(inputStream, path.resolve(uniqueFileName), StandardCopyOption.REPLACE_EXISTING);


        } catch (Exception e) {

        }


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
