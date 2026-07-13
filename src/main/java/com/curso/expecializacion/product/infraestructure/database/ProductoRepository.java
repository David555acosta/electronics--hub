package com.curso.expecializacion.product.infraestructure.database;
import com.curso.expecializacion.category.domain.Category;
import com.curso.expecializacion.category.infraestructure.CategoryEntityMapper;
import com.curso.expecializacion.category.infraestructure.CategoryRepository;
import com.curso.expecializacion.producDetail.domain.ProductDetail;
import com.curso.expecializacion.product.application.command.update.UpdateProductCreateRequest;
import com.curso.expecializacion.product.common.domain.PaginationQuery;
import com.curso.expecializacion.product.common.domain.PaginationResult;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.domain.ProductFilter;
import com.curso.expecializacion.product.domain.product_repository;
import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;
import com.curso.expecializacion.product.infraestructure.database.entity.ProductSpecificationEntity;
import com.curso.expecializacion.product.infraestructure.database.mapper.ProductoEntityMapper;
import com.curso.expecializacion.product.infraestructure.database.repositoryDBProducts.QueryProductsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductoRepository implements product_repository {


    private final ProductoEntityMapper productoEntityMapper;
    private final CategoryEntityMapper categoryEntityMapper;

    private final QueryProductsRepository repository;
    private final CategoryRepository categoryRepository;



    @Override
    public Product save(Product product) {
        log.info("Guardando producto REPOSITORY");
        ProductEntity productEntity = productoEntityMapper.mapToProductEntity(product);
        ProductEntity guardado = repository.save(productEntity);
        return productoEntityMapper.mapToProduct(guardado);
    }

    @Cacheable(value = "products", key = "#id")
    @Override
    public Optional<Product> findById(Integer id) {
        log.info("Cacheable , PRODUCT FIND HANDLER REPOSITORIO , Codigo:{}", id);
        return repository.findById(id).map(productoEntityMapper::mapToProduct);
    }

    @Override
    public PaginationResult<Product> findAll(PaginationQuery paginationQuery , ProductFilter productFilter) {
        log.info("ESTOY EN CAPA REPOSITORI TRAYENDO TODOS INICIANDO");
        PageRequest pageRequest = PageRequest.of(
                paginationQuery.getPage(),
                paginationQuery.getSize(),
                Sort.by(Sort.Direction.fromString(paginationQuery.getDirection()),
                paginationQuery.getSortby())
        );

        log.info("ESTOY EN CAPA REPOSITORI TRAYENDO TODOS CREADO PAGE REQUEST");

        Specification<ProductEntity> specification = Specification.allOf(
                ProductSpecificationEntity.byName(productFilter.getNombre())
                        .and(ProductSpecificationEntity.byDescription(productFilter.getDescripcion()))
                        .and(ProductSpecificationEntity.byPrecio(productFilter.getPriceMin(),productFilter.getPriceMax()))
        );

        log.info("ESTOY EN CAPA REPOSITORI TRAYENDO TODOS CREADA SPECIFICACION");
        Page<ProductEntity> page = repository.findAll(specification , pageRequest);

        return new PaginationResult<>(
                page.getContent().stream().map(productoEntityMapper::mapToProduct).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                (int) page.getTotalPages()
        );
    }

    @Override
    public void update(Product product , UpdateProductCreateRequest request) {
        ProductDetail productDetail = product.getProductDetail();
        productDetail.setProvider(request.getProvider());



        Category category = categoryRepository.findById(request.getCategoryId()).
                map(categoryEntityMapper::mapToCategory).orElseThrow(() ->
                        new RuntimeException("Categoria no encontrada"));


        product.getCategory().add(category);

        Product actualizado = Product.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio()).build();

        repository.save(productoEntityMapper.mapToProductEntity(actualizado));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
