package com.curso.expecializacion.product.infraestructure.api;

import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.infraestructure.api.dto.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface product_api {
    //ResponseEntity<List<Product>> todos(@RequestParam(required = false) Integer limit);

    //ResponseEntity<Product> filtrarPorId(@PathVariable Integer id);

    ResponseEntity<Void> save(@RequestBody ProductDTO product);

    //ResponseEntity<Product> update(@RequestBody Product product);

    //ResponseEntity<Void> delete(@PathVariable Integer id);
}
