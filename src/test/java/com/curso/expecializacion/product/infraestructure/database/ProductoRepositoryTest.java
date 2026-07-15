package com.curso.expecializacion.product.infraestructure.database;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;
import com.curso.expecializacion.product.infraestructure.database.mapper.ProductoEntityMapper;
import com.curso.expecializacion.product.infraestructure.database.repositoryDBProducts.QueryProductsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductoRepositoryTest {

    @Mock
    private ProductoEntityMapper productoEntityMapper;
    @Mock
    private QueryProductsRepository repository;
    @InjectMocks
    private ProductoRepository productoRepository;


    @Test
    void notFoundById() {
        Optional<Product> productEntityOptional = productoRepository.findById(30);
        assertTrue(productEntityOptional.isEmpty());
    }

    @Test
    void foundById() {
        when(repository.findById(1)).thenReturn(Optional.of(new ProductEntity()));
        when(productoEntityMapper.mapToProduct(any(ProductEntity.class))).
                thenReturn(Product.builder().codigo(1).build());

        Optional<Product> productEntityOptional = productoRepository.findById(1);
        assertTrue(productEntityOptional.isPresent());

    }


    //Eliminar
    @Test
    void delete() {

        Integer id = 1;
        ProductEntity entityExistente = new ProductEntity();
        entityExistente.setCodigo(id);
        Product domain = Product.builder().codigo(id).build();


        when(repository.findById(id)).thenReturn(Optional.of(entityExistente));


        when(productoEntityMapper.mapToProduct(entityExistente)).thenReturn(domain);


        Optional<Product> resultado = productoRepository.delete(id);


        assertTrue(resultado.isPresent(), "El opcional debería contener el producto eliminado");
        assertEquals(id, resultado.get().getCodigo());


        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).delete(entityExistente);
        verify(productoEntityMapper, times(1)).mapToProduct(entityExistente);

    }
}