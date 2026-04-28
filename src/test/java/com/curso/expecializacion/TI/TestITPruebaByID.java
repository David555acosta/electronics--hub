package com.curso.expecializacion.TI;

import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.infraestructure.api.dto.ProductDTO;
import com.curso.expecializacion.product.infraestructure.database.ProductoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestITPruebaByID {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductoRepository productoRepository;

    @BeforeEach
    public void setUp() {
        log.info("Iniciando TestITPruebaByID");
        productoRepository.save(Product.builder().codigo(1).nombre("Martillo").descripcion("con wifi").precio(150.00).build());
    }

    @AfterEach
    public void tearDown() {
        log.info("Finalizando TestITPruebaByID");
        productoRepository.delete(1);
    }

    @Test
    void existGetProductByID() {
        log.info("Iniciando TestITPruebaByID");
        ResponseEntity<ProductDTO> response = restTemplate.getForEntity("http://localhost:9526/productos/v1/1", ProductDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getCodigo());
        assertEquals("Martillo", response.getBody().getNombre());
        assertEquals("con wifi", response.getBody().getDescripcion());
        Assertions.assertNotNull(response.getBody());
    }
}
