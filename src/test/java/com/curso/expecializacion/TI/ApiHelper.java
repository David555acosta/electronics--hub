package com.curso.expecializacion.TI;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiHelper {

    private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJkYXZpZF9kZXYiLCJlbWFpbCI6ImRhdml" +
            "kQGV4YW1wbGUuY29tIiwiaWF0IjoxNzM1Njg2NDAwLCJleHAiOjI1MzQwMDIzMDAwMH0.36Z3R-Z_1J4E--8c5vA2_D";

    @Bean
    public TestRestTemplate getRestTemplate() {
        return new TestRestTemplate(new RestTemplateBuilder()
                .defaultHeader("Authorization", "Bearer " .concat(token))
                .setConnectTimeout(java.time.Duration.ofSeconds(5))
                .rootUri("http://localhost:9532")
        );
    }
}

