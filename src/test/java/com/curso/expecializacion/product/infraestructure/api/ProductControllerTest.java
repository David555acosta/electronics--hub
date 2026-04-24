package com.curso.expecializacion.product.infraestructure.api;

import com.curso.expecializacion.product.application.query.getAll.AllGetProductRequest;
import com.curso.expecializacion.product.application.query.getAll.AllGetProductResponse;
import com.curso.expecializacion.product.common.mediator.Mediator;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.infraestructure.api.dto.ProductDTO;
import com.curso.expecializacion.product.infraestructure.api.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private Mediator mediator;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductController productController;

    @Test
    public void getAllProducts() {

        AllGetProductResponse productResponse = new AllGetProductResponse(List.of(
                Product.builder().codigo(1).build(),
                Product.builder().codigo(2).build()
        ));


        when(mediator.dispacth(new AllGetProductRequest())).thenReturn(productResponse);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setCodigo(1);

        when(productMapper.mapToProductDto(any(Product.class))).thenReturn(productDTO);

        ResponseEntity<List<ProductDTO>> response = productController.findAll("5");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        List<ProductDTO> productDTOS = response.getBody();
        assertEquals(productDTOS.size(), productDTOS.size());
    }
}