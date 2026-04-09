package com.curso.expecializacion.product.application.query.getbyid;


import com.curso.expecializacion.product.domain.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Data
public class GetProductByIdResponse {
    private Product product;

}
