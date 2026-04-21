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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/productos/v1")
public class ProductController implements product_api {


    private final Mediator mediator;
    private final ProductMapper productMapper;


    @Override
    @PostMapping("")
    public ResponseEntity<Void> save(@RequestBody @Valid CreateProductDTO product) {
        ProductCreateRequest request = productMapper.mapTocreateProductoRequest(product);
        mediator.dispacth(request);
        return ResponseEntity.created(URI.create("/productos/v1".concat(product.getCodigo().toString()))).build();
    }

    @Override
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody @Valid UpdateProductDTO productDTO) {
        UpdateProductCreateRequest request = productMapper.mapToUpdateProductRequest(productDTO);
        mediator.dispacth(request);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestBody Integer id) {
        mediator.dispacth(new DeleteProductRequest(id));
        return ResponseEntity.noContent().build();
    }


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> filtrarPorId(@PathVariable Integer id) {
        GetProductByIdResponse response = mediator.dispacth(new GetProductByIdRequest(id));
        ProductDTO productDto = productMapper.mapToProduct(response.getProduct());
        return ResponseEntity.ok(productDto);
    }

    @Override
    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> findAll() {
        AllGetProductResponse response = mediator.dispacth(new AllGetProductRequest());
        List<ProductDTO> productDTOS = response.getProduct().stream().map(productMapper::mapToProduct).toList();
        return ResponseEntity.ok(productDTOS);
    }


}
