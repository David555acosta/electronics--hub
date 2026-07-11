package com.curso.expecializacion.product.infraestructure.database.seeder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;
import com.curso.expecializacion.product.infraestructure.database.repositoryDBProducts.QueryProductsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(1)
public class ProductSeeder implements CommandLineRunner {
    private final QueryProductsRepository queryProductsRepository;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {

        long count = queryProductsRepository.count();

        if (count == 0) {
            Resource resource = resourceLoader.getResource("classpath:products.json");

            List<ProductEntity> products = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<ProductEntity>>() {}
            );

            queryProductsRepository.saveAll(products);
        }
    }
}
