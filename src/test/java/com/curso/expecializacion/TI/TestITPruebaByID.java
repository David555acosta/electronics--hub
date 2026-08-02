package com.curso.expecializacion.TI;
import com.curso.expecializacion.config.security.jwt.JwtUtils;
import com.curso.expecializacion.product.infraestructure.api.dto.ProductDTO;
import com.curso.expecializacion.user.infraestructure.database.entity.UsuarioEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestITPruebaByID {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private MockMvc mockMvc;


    //Envío de una request simulando un logueo con las credenciales que se cargan en la db embebida
    @BeforeEach
    void setUp() {
        String tokenReal = jwtUtils.generateAccessToken("david_admin");


        restTemplate.getRestTemplate().getInterceptors().clear();

        restTemplate.getRestTemplate().getInterceptors().add((request, body, execution) -> {
            request.getHeaders().setBearerAuth(tokenReal);
            return execution.execute(request, body);
        });
    }


    @Sql(value = "/TI/finByID/data.sql" , executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/TI/clean.sql" , executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void testProducto1() {
        log.info("Iniciando Test testProducto1");
        ResponseEntity<ProductDTO> response = restTemplate.getForEntity("/productos/v1/1", ProductDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getCodigo());
        assertEquals("pc facha", response.getBody().getNombre());
        assertEquals("facherisima", response.getBody().getDescripcion());
        assertEquals(100.00, response.getBody().getPrecio());
        log.info("Finalizando Test testProducto1");
    }

    @Sql(value = "/TI/finByID/data.sql" , executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/TI/clean.sql" , executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    void eliminarProductoConLogeoAdmin() {
        log.info("Iniciando Test eliminarProductoConLogeoAdmin");

        ResponseEntity<UsuarioEntity> response = restTemplate.getForEntity("/user/delete/2", UsuarioEntity.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        log.info("Finalizando Test eliminarProductoConLogeoAdmin");
    }


    @Test
    @WithMockUser(username = "david_dev", roles = {"USER", "ADMIN"})
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
