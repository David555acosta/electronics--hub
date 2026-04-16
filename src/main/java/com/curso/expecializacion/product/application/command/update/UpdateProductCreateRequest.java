package com.curso.expecializacion.product.application.command.update;

import com.curso.expecializacion.product.commongMediator.Request;
import lombok.Data;


@Data
public class UpdateProductCreateRequest implements Request<Void> {
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;
}
