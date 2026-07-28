package com.curso.expecializacion.product.command.create;


import com.curso.expecializacion.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class ProductCreateResponse {
    private Product product;
}
