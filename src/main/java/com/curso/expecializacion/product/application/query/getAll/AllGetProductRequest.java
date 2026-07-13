package com.curso.expecializacion.product.application.query.getAll;

import com.curso.expecializacion.product.common.domain.PaginationQuery;
import com.curso.expecializacion.product.common.mediator.Request;
import com.curso.expecializacion.product.domain.ProductFilter;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AllGetProductRequest implements Request<AllGetProductResponse> {
    PaginationQuery paginationQuery;
    ProductFilter productFilter;
}
