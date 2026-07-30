package com.curso.expecializacion.user.application.login;


import com.curso.expecializacion.config.security.jwt.JwtUtils;
import com.curso.expecializacion.product.common.mediator.RequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserHandler implements RequestHandler<LoginUserRequest, LoginUserResponse> {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public LoginUserResponse handle(LoginUserRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = jwtUtils.generateAccessToken(authentication.getName());

        return new LoginUserResponse(token);
    }

    @Override
    public Class<LoginUserRequest> getRequesType() {
        return LoginUserRequest.class;
    }
}
