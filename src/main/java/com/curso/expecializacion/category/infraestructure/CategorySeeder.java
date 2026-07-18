package com.curso.expecializacion.category.infraestructure;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(1)
@Profile("!test")
public class CategorySeeder implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;


    @Override
    public void run(String... args) throws Exception {

        long count = categoryRepository.count();

        if (count == 0) {
            Resource resource = resourceLoader.getResource("classpath:category.json");

            List<CategoryEntity> categoryEntities = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<CategoryEntity>>() {}
            );

            categoryRepository.saveAll(categoryEntities);
        }

    }

}
