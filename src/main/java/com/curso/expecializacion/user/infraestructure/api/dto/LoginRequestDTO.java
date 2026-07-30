package com.curso.expecializacion.user.infraestructure.api.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;
}
