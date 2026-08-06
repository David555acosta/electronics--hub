package com.curso.expecializacion.user.application.query.update;

import com.curso.expecializacion.product.common.mediator.Request;
import lombok.Data;

@Data
public class UpdateUserRequest implements Request<Void> {

    private Integer id;
    private String nickname;
    private String email;

}
