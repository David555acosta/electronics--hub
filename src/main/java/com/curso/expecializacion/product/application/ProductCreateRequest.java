package com.curso.expecializacion.product.application;

import com.curso.expecializacion.product.commongMediator.Request;
import lombok.Data;


@Data
public class ProductCreateRequest implements Request<Void> {
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;
}
