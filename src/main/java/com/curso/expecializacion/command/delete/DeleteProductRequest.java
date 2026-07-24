package com.curso.expecializacion.command.delete;

import com.curso.expecializacion.product.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class DeleteProductRequest implements Request<Void> {
    private Integer codigo;
}
