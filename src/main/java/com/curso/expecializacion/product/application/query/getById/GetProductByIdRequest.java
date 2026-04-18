package com.curso.expecializacion.product.application.query.getById;

import com.curso.expecializacion.product.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetProductByIdRequest implements Request<GetProductByIdResponse> {
    private Integer codigo;

}
