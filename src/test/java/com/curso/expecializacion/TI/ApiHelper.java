package com.curso.expecializacion.TI;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ApiHelper {
    @Bean
    public TestRestTemplate getRestTemplate() {
        return new TestRestTemplate(new RestTemplateBuilder()
                .basicAuthentication("", "")
                .setConnectTimeout(java.time.Duration.ofSeconds(5))
        );
    }
}

