package com.curso.expecializacion.user.application.login;

import com.curso.expecializacion.product.common.mediator.Request;
import lombok.Data;

@Data
public class LoginUserRequest implements Request<LoginUserResponse> {
    private String username;
    private String password;
}
