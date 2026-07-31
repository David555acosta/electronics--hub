package com.curso.expecializacion.TI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ApiHelper {

    @Value("${spring.jwt.secret.key}")
    private String token;

    @Bean
    public TestRestTemplate getRestTemplate() {
        return new TestRestTemplate(new RestTemplateBuilder()
                .defaultHeader("Authorization", "Bearer " .concat(token))
                .setConnectTimeout(java.time.Duration.ofSeconds(5))
                .rootUri("http://localhost:9532")
        );
    }
}

