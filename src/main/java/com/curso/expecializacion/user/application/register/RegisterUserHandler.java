package com.curso.expecializacion.user.application.register;


import com.curso.expecializacion.product.common.mediator.RequestHandler;

public class RegisterUserHandler implements RequestHandler<RegisterUserRequest, RegisterUserResponse> {

    private String token;

    public RegisterUserHandler() {
        super();
    }

    @Override
    public RegisterUserResponse handle(RegisterUserRequest request) {
        return null;
    }

    @Override
    public Class<RegisterUserRequest> getRequesType() {
        return null;
    }
}
