package com.curso.expecializacion.product.infraestructure.database.repositoryDBProducts;
import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QueryProductsRepository extends JpaRepository<ProductEntity, Integer> , JpaSpecificationExecutor<ProductEntity> {

    @EntityGraph(attributePaths = {"productDetailEntity", "reviews" , "categoryEntities"})
    Page<ProductEntity> findAll(Specification<ProductEntity> specification , Pageable pageable);

    @EntityGraph(attributePaths = {"productDetailEntity", "reviews" , "categoryEntities"})
    Optional<ProductEntity> findById(Integer id);

    Optional<ProductEntity> deleteByProductId(Integer id);
}
