package com.curso.expecializacion.product.application.command.delete;

import com.curso.expecializacion.product.commongMediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class DeleteProductRequest implements Request<Void> {
    private Integer codigo;
}
