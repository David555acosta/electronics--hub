package com.curso.expecializacion.product.application.query.getById;

import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.domain.product_repository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetProductByIdHandlerTest {

    @Mock
    private product_repository repository;

    @InjectMocks
    private GetProductByIdHandler handler;

    @Test
    void scandalousNull() {
        Integer productId = 1;
        Product mockProduct = Product.builder().codigo(productId).build();
        GetProductByIdRequest request = new GetProductByIdRequest(productId);
        Mockito.when(repository.findById(productId)).thenReturn(Optional.of(mockProduct));

        GetProductByIdResponse response = handler.handle(request);

        assertNotNull(response);
        assertEquals(mockProduct, response.getProduct());
    }


    @Test
    void exception() {
        Integer productId = 1;
        GetProductByIdRequest request = new GetProductByIdRequest(productId);
        Mockito.when(repository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> handler.handle(request));
    }

    @Test
    void getRequesType() {
        assertEquals(GetProductByIdRequest.class, handler.getRequesType());
    }
}