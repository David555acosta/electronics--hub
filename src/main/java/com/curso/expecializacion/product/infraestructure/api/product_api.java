package com.curso.expecializacion.product.infraestructure.api;

import com.curso.expecializacion.product.common.domain.PaginationResult;
import com.curso.expecializacion.product.infraestructure.api.dto.CreateProductDTO;
import com.curso.expecializacion.product.infraestructure.api.dto.ProductDTO;
import com.curso.expecializacion.product.infraestructure.api.dto.UpdateProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


public interface product_api {
    //ResponseEntity<List<Product>> todos(@RequestParam(required = false) Integer limit);

    ResponseEntity<ProductDTO> filtrarPorId(@PathVariable Integer id);

    ResponseEntity<Void> save(@RequestBody CreateProductDTO product);

    ResponseEntity<Void> update(@RequestBody UpdateProductDTO productDTO);

    ResponseEntity<Void> delete(@PathVariable Integer id);

    ResponseEntity<PaginationResult<ProductDTO>> findAll(int pageSize, int pageNumber, String sortby, String direction, String name, String description, Double priceMin, Double priceMax);
}
