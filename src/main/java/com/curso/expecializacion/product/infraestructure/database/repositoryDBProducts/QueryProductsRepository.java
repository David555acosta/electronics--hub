package com.curso.expecializacion.product.infraestructure.database.repositoryDBProducts;
import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QueryProductsRepository extends JpaRepository<ProductEntity, Integer> {


}
