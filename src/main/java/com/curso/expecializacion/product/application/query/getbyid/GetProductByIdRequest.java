package com.curso.expecializacion.product.application.query.getbyid;

import com.curso.expecializacion.product.commongMediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetProductByIdRequest implements Request<GetProductByIdResponse> {
    private Integer codigo;

}
