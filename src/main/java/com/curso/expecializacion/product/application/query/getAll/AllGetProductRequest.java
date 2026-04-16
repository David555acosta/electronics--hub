package com.curso.expecializacion.product.application.query.getAll;

import com.curso.expecializacion.product.application.query.getById.GetProductByIdResponse;
import com.curso.expecializacion.product.commongMediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AllGetProductRequest implements Request<allGetProductResponse> {

}
