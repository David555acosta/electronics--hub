package com.curso.expecializacion.user.domain.exception;

import com.curso.expecializacion.config.exceptions.ErrorMesage;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint  implements AuthenticationEntryPoint{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String message = (String) request.getAttribute("jwt_error");
        if (message == null) {
            message = "Token JWT inválido, expirado o no proporcionado";
        }

        ErrorMesage errorMessage = new ErrorMesage(
                message,
                authException.getClass().getSimpleName(),
                request.getRequestURI()
        );

        response.getWriter().write(objectMapper.writeValueAsString(errorMessage));
    }
}
