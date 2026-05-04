package com.curso.expecializacion.product.application.command.create;


import com.curso.expecializacion.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class ProductCreateResponse {
    private Product product;
}
