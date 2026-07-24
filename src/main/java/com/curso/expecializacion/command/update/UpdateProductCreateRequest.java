package com.curso.expecializacion.command.update;

import com.curso.expecializacion.product.common.mediator.Request;
import lombok.Data;



@Data
public class UpdateProductCreateRequest implements Request<Void> {
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String provider;
    private Integer categoryId;
}
