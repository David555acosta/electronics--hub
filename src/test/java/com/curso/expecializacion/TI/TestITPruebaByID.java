package com.curso.expecializacion.TI;
import com.curso.expecializacion.product.infraestructure.api.dto.ProductDTO;
import com.curso.expecializacion.product.infraestructure.database.ProductoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestITPruebaByID {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductoRepository productoRepository;

    @Sql(value = "/TI/finByID/data.sql" , executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/TI/clean.sql" , executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void existGetProductByID() {
        log.info("Iniciando Test ITPruebaByID");
        ResponseEntity<ProductDTO> response = restTemplate.getForEntity("/productos/v1/1", ProductDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getCodigo());
        assertEquals("pc facha", response.getBody().getNombre());
        assertEquals("facherisima", response.getBody().getDescripcion());
        assertEquals(100.00, response.getBody().getPrecio());
        log.info("Finalizando Test ITPruebaByID");
    }


    @Test
    @Sql(value = "/TI/clean.sql" , executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
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
