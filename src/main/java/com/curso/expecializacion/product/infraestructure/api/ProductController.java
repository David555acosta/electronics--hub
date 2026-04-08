package com.curso.expecializacion.product.infraestructure.api;

import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.infraestructure.database.ProductoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos/v1")
public class ProductController implements product_api {

    private ProductoRepository productoRepository;


    @GetMapping("/todos")
    public ResponseEntity<List<Product>> todos(@RequestParam(required = false) Integer limit) {
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> filtrarPorId(@PathVariable Integer id) {
        Optional<Product> productOptional = productList.stream().filter(p -> p.getCodigo().equals(id)).findFirst();
        return productOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }


    @PostMapping()
    public ResponseEntity<Void> save(@RequestBody Product product) {
        productList.add(product);
        return ResponseEntity.created(URI.create("/productos/v1")).build();
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
    }
}
