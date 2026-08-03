package com.curso.expecializacion.user.application.query.finByUserName;


import com.curso.expecializacion.product.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindByUserNameRequest implements Request<FindByUserNameResponse> {
    private String userName;
}
