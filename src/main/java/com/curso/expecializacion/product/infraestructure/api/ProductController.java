package com.curso.expecializacion.product.infraestructure.api;

import com.curso.expecializacion.product.application.command.create.ProductCreateRequest;
import com.curso.expecializacion.product.application.command.create.ProductCreateResponse;
import com.curso.expecializacion.product.application.command.delete.DeleteProductRequest;
import com.curso.expecializacion.product.application.command.update.UpdateProductCreateRequest;
import com.curso.expecializacion.product.application.query.getAll.AllGetProductRequest;
import com.curso.expecializacion.product.application.query.getAll.AllGetProductResponse;
import com.curso.expecializacion.product.application.query.getById.GetProductByIdRequest;
import com.curso.expecializacion.product.application.query.getById.GetProductByIdResponse;
import com.curso.expecializacion.product.common.domain.PaginationQuery;
import com.curso.expecializacion.product.common.domain.PaginationResult;
import com.curso.expecializacion.product.common.mediator.Mediator;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.domain.ProductFilter;
import com.curso.expecializacion.product.infraestructure.api.dto.CreateProductDTO;
import com.curso.expecializacion.product.infraestructure.api.dto.ProductDTO;
import com.curso.expecializacion.product.infraestructure.api.dto.UpdateProductDTO;
import com.curso.expecializacion.product.infraestructure.api.mapper.ProductMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;


@RestController
@Tag(name = "Productos ", description = "Product API")
@RequiredArgsConstructor
@RequestMapping("/productos/v1")
@Slf4j
public class ProductController implements product_api {


    private final Mediator mediator;
    private final ProductMapper productMapper;


    @Override
    @Operation(summary = "Guardar producto", description = "Guardar producto")
    @PostMapping("")
    public ResponseEntity<Void> save(@ModelAttribute @Valid CreateProductDTO product) {
        ProductCreateRequest request = productMapper.mapTocreateProductoRequest(product);
        ProductCreateResponse response = mediator.dispacth(request);
        Product productX = response.getProduct();
        return ResponseEntity.created(URI.create("/productos/v1".concat(productX.getCodigo().toString()))).build();
    }

    @Override
    @Operation(summary = "Actualizar producto", description = "Actualizar producto")
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody @Valid UpdateProductDTO productDTO) {
        log.info("Capa Controller , actualizando producto");
        UpdateProductCreateRequest request = productMapper.mapToUpdateProductRequest(productDTO);
        mediator.dispacth(request);
        log.info("Capa Controller , ACTUALIZADO producto con Nombre:{}", productDTO.getNombre());
        return ResponseEntity.noContent().build();
    }

    @Override
    @Operation(summary = "Eliminar producto", description = "Eliminar producto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestBody Integer id) {
        log.info("Capa Controller , eliminando producto  con Id:{}", id);
        mediator.dispacthAsync(new DeleteProductRequest(id));
        log.info("Capa Controller , Eliminado producto con Id:{}", id);
        return ResponseEntity.noContent().build();
    }


    @Override
    @Operation(summary = "Filtrar por ID", description = "Filtrar por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> filtrarPorId(@PathVariable Integer id) {
        log.info("Capa Controller , obteniendo producto  con Id:{}", id);
        GetProductByIdResponse response = mediator.dispacth(new GetProductByIdRequest(id));
        ProductDTO productDto = productMapper.mapToProductDto(response.getProduct());
        log.info("Capa Controller , OBTENIDO producto con Id:{}", id);
        return ResponseEntity.ok(productDto);
    }

    @Operation(summary = "Traer todos", description = "Traer todos")
    @GetMapping("")
    @Override
    public ResponseEntity<PaginationResult<ProductDTO>> findAll
            (@RequestParam(defaultValue = "0") int pageSize,
             @RequestParam(defaultValue = "5") int pageNumber,
             @RequestParam(defaultValue = "codigo") String sortby,
             @RequestParam(defaultValue = "asc") String direction,
             @RequestParam(required = false) String nombre,
             @RequestParam(required = false) String descripcion,
             @RequestParam(required = false) Double priceMin,
             @RequestParam(required = false) Double priceMax
            ) {
        log.info("Capa Controller , TRAYENDO TODOS");
        PaginationQuery paginationQuery = new PaginationQuery(pageSize, pageNumber, sortby, direction);
        ProductFilter productFilter = new ProductFilter(nombre, descripcion, priceMin, priceMax);
        AllGetProductRequest  allgetRequest = new AllGetProductRequest(paginationQuery, productFilter);
        AllGetProductResponse response = mediator.dispacth(allgetRequest);
        PaginationResult<Product> productsPage = response.getProductsPage();

        PaginationResult<ProductDTO> productDTOPaginationResult = new PaginationResult<>(
                productsPage.getContent().stream().map(productMapper::mapToProductDto).toList(),
                productsPage.getPage(),
                productsPage.getSize(),
                productsPage.getTotalPages(),
                productsPage.getTotalElements()
        );
        log.info("Capa Controller , TODOS , CANTIDAD: {}", productsPage.getSize());
        return ResponseEntity.ok(productDTOPaginationResult);
    }
}
