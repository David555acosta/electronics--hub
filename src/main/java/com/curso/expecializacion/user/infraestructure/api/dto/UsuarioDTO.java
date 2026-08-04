package com.curso.expecializacion.user.infraestructure.api.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;


@Data
@RequiredArgsConstructor
public class UsuarioDTO {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Set<RolDTO> rols;
}
