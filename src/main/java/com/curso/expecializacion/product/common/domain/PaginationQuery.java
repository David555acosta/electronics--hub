package com.curso.expecializacion.product.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginationQuery {
    private  int page;
    private int size;
    private String sortby;
    private String direction;
}
