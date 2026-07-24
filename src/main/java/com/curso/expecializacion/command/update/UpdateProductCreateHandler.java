package com.curso.expecializacion.command.update;


import com.curso.expecializacion.category.domain.Category;
import com.curso.expecializacion.category.infraestructure.CategoryEntityMapper;
import com.curso.expecializacion.category.infraestructure.CategoryRepository;
import com.curso.expecializacion.producDetail.domain.ProductDetail;
import com.curso.expecializacion.product.common.mediator.RequestHandler;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.domain.product_repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateProductCreateHandler implements RequestHandler<UpdateProductCreateRequest, Void> {

    private final product_repository repository;
    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;


    @Override
    public Void handle(UpdateProductCreateRequest request) {
        log.info("Actualizando producto , PRODUCT UPDATE HANDLER , Codigo:{}", request.getCodigo());

        Product product = repository.findById(request.getCodigo())
                .orElseThrow(() -> new RuntimeException("No se encontró el producto."));

        ProductDetail productDetail = product.getProductDetail();
        productDetail.setProvider(request.getProvider());



        Category category = categoryRepository.findById(request.getCategoryId()).
                map(categoryEntityMapper::mapToCategory).orElseThrow(() ->
                        new RuntimeException("Categoria no encontrada"));


        product.getCategory().add(category);
        product.setNombre(request.getNombre());
        product.setDescripcion(request.getDescripcion());
        product.setPrecio(request.getPrecio());


        repository.update(product) ;
        log.info("producto , PRODUCT UPDATE HANDLER , Codigo:{}", request.getCodigo());
        return null;
    }

    @Override
    public Class<UpdateProductCreateRequest> getRequesType() {

        return UpdateProductCreateRequest.class;
    }
}
