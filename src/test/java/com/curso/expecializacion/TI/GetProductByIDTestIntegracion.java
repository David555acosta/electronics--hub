package com.curso.expecializacion.TI;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RequiredArgsConstructor
public class GetProductByIDTestIntegracion {
    private final TestRestTemplate testRestTemplate;

}
