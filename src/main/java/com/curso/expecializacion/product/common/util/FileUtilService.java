package com.curso.expecializacion.product.common.util;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileUtilService {
    public String SaveProduct(MultipartFile file) {

        String uniqueFileName;

        //Flujo de bytes
        try (InputStream inputStream = file.getInputStream()) {

            //Obtener el nombre del archivo , sacando las "/" y si es null lanzanod excepcion
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            //Obtener un numero random y concatenandolo al nombre del archivo limpio
            uniqueFileName = UUID.randomUUID().toString().concat("_").concat(fileName);

            Path path = Path.of("src/main/resources/products");

            if (!Files.exists(path)) {

                Files.createDirectories(path);
            }

            Files.copy(inputStream, path.resolve(uniqueFileName), StandardCopyOption.REPLACE_EXISTING);


        } catch (Exception e) {
            throw new RuntimeException("Error al cargar archivo");
        }

        return uniqueFileName;
    }

}
