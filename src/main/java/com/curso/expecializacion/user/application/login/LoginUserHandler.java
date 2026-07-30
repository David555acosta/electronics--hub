package com.curso.expecializacion.user.application.login;


import com.curso.expecializacion.product.common.mediator.RequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserHandler implements RequestHandler<LoginUserRequest, LoginUserResponse> {

    @Override
    public LoginUserResponse handle(LoginUserRequest request) {
        return null;
    }

    @Override
    public Class<LoginUserRequest> getRequesType() {
        return null;
    }
}
