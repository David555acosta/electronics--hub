package com.curso.expecializacion.product.infraestructure.api;

import com.curso.expecializacion.product.application.command.create.ProductCreateRequest;
import com.curso.expecializacion.product.application.command.delete.DeleteProductRequest;
import com.curso.expecializacion.product.application.command.update.UpdateProductCreateRequest;
import com.curso.expecializacion.product.application.query.getAll.AllGetProductRequest;
import com.curso.expecializacion.product.application.query.getAll.AllGetProductResponse;
import com.curso.expecializacion.product.application.query.getById.GetProductByIdRequest;
import com.curso.expecializacion.product.application.query.getById.GetProductByIdResponse;
import com.curso.expecializacion.product.common.mediator.Mediator;
import com.curso.expecializacion.product.infraestructure.api.dto.CreateProductDTO;
import com.curso.expecializacion.product.infraestructure.api.dto.ProductDTO;
import com.curso.expecializacion.product.infraestructure.api.dto.UpdateProductDTO;
import com.curso.expecializacion.product.infraestructure.api.mapper.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/productos/v1")
@Slf4j
public class ProductController implements product_api {


    private final Mediator mediator;
    private final ProductMapper productMapper;



    @Override
    @PostMapping("")
    public ResponseEntity<Void> save(@ModelAttribute @Valid CreateProductDTO product) {
        ProductCreateRequest request = productMapper.mapTocreateProductoRequest(product);
        mediator.dispacth(request);
        return ResponseEntity.created(URI.create("/productos/v1".concat(product.getCodigo().toString()))).build();
    }

    @Override
    @PutMapping("")
    public ResponseEntity<Void> update(@ModelAttribute @Valid UpdateProductDTO productDTO) {
        log.info("Capa Controller , actualizando producto  con Id:{}", productDTO.getCodigo());
        UpdateProductCreateRequest request = productMapper.mapToUpdateProductRequest(productDTO);
        mediator.dispacth(request);
        log.info("Capa Controller , ACTUALIZADO producto con Id:{}", productDTO.getCodigo());
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestBody Integer id) {
        log.info("Capa Controller , eliminando producto  con Id:{}", id);
        mediator.dispacthAsync(new DeleteProductRequest(id));
        log.info("Capa Controller , Eliminado producto con Id:{}", id);
        return ResponseEntity.noContent().build();
    }


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> filtrarPorId(@PathVariable Integer id) {
        log.info("Capa Controller , obteniendo producto  con Id:{}", id);
        GetProductByIdResponse response = mediator.dispacth(new GetProductByIdRequest(id));
        ProductDTO productDto = productMapper.mapToProductDto(response.getProduct());
        log.info("Capa Controller , OBTENIDO producto con Id:{}", id);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("")
    @Override
    public ResponseEntity<List<ProductDTO>> findAll(@RequestParam(required = false) String pageSize) {
        log.info("Capa Controller , TRAYENDO TODOS");
        AllGetProductResponse response = mediator.dispacth(new AllGetProductRequest());
        List<ProductDTO> productDTOS = response.getProduct().stream().map(productMapper::mapToProductDto).toList();
        log.info("Capa Controller , TODOS , CANTIDAD: {}", productDTOS.size());
        return ResponseEntity.ok(productDTOS);
    }


}
