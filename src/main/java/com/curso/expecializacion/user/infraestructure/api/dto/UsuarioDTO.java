package com.curso.expecializacion.user.infraestructure.api.dto;
import com.curso.expecializacion.user.infraestructure.database.entity.RolEntity;
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
}
