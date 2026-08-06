package com.curso.expecializacion.user.application.register;

import com.curso.expecializacion.product.common.mediator.Request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterUserRequest implements Request<RegisterUserResponse> {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String nickname;

    @NotBlank
    private String password;
    private Set<String> role;
}
