package com.curso.expecializacion.product.infraestructure.api;

import com.curso.expecializacion.product.application.ProductCreateRequest;
import com.curso.expecializacion.product.commongMediator.Mediator;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.infraestructure.api.dto.ProductDTO;
import com.curso.expecializacion.product.infraestructure.api.mapper.ProductMapper;
import com.curso.expecializacion.product.infraestructure.database.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/productos/v1")
public class ProductController implements product_api {

    private final Mediator mediator;
    private final ProductMapper productMapper;


    @Override
    @PostMapping()
    public ResponseEntity<Void> save(ProductDTO product) {
        ProductCreateRequest request = productMapper.mapTocreateProductoRequest(product);
        mediator.dispacth(request);
        return ResponseEntity.created(URI.create("/productos/v1".concat(product.getCodigo().toString()))).build();
    }


   /*
   *  @GetMapping("/todos")
    public ResponseEntity<List<Product>> todos(@RequestParam(required = false) Integer limit) {
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> filtrarPorId(@PathVariable Integer id) {
        Optional<Product> productOptional = productList.stream().filter(p -> p.getCodigo().equals(id)).findFirst();
        return productOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }


    @PutMapping()
    public ResponseEntity<Product> update(@RequestBody Product product) {
        Product productoSelecionado = productList.stream().filter(p -> p.getCodigo().equals(product.getCodigo())).findFirst().orElseThrow(() -> new RuntimeException("No se encontro producto con el id" + product.getCodigo()));

        productoSelecionado.setNombre(product.getNombre());
        productoSelecionado.setDescripcion(product.getDescripcion());
        productoSelecionado.setPrecio(product.getPrecio());

        return ResponseEntity.ok(productoSelecionado);


    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productList.removeIf(p -> p.getCodigo().equals(id));
        return ResponseEntity.noContent().build();
    }*/
}
