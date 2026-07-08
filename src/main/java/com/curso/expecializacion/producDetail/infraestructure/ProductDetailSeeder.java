package com.curso.expecializacion.producDetail.infraestructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;
import com.curso.expecializacion.product.infraestructure.database.repositoryDBProducts.QueryProductsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductDetailSeeder implements CommandLineRunner {
    private final ProductDetailRepository productDetailRepository;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;


    @Override
    public void run(String... args) throws Exception {

        long count = productDetailRepository.count();

        if (count == 0) {
            Resource resource = resourceLoader.getResource("classpath:products.json");

            List<ProductDetailEntity> productDetailEntities = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<ProductDetailEntity>>() {}
            );

            productDetailRepository.saveAll(productDetailEntities);
        }

    }
}
