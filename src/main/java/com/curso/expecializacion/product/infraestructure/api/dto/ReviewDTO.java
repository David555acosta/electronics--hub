package com.curso.expecializacion.product.infraestructure.api.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private Integer id;
    private String comment;
    private Integer source;
}
