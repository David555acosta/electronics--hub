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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestITPruebaByID {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

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
        log.info("Iniciando Test ITPruebaByID");
        String url = "http://localhost:" + port + "/productos/v1/1";

        ResponseEntity<ProductDTO> response =
                restTemplate.getForEntity(url, ProductDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getCodigo());
        assertEquals("Martillo", response.getBody().getNombre());
        assertEquals("con wifi", response.getBody().getDescripcion());
        log.info("Finalizando Test ITPruebaByID");
    }


    @Test
    public void saveProduct() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.txt",
                "text/plain",
                "Hola mundo".getBytes()
        );

        mockMvc.perform(
                        multipart("/productos/v1")
                                .file(file)
                                .param("codigo", "2")
                                .param("nombre", "Name2")
                                .param("descripcion", "Desc2")
                                .param("precio", "150")
                )
                .andExpect(status().isCreated());
    }
}
