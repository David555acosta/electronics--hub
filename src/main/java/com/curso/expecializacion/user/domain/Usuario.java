package com.curso.expecializacion.user.domain;

import com.curso.expecializacion.user.infraestructure.database.entity.RolEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class Usuario {

    private Integer id;
    private String username;
    private String email;
    private String password;
    private Set<RolEntity> rols;
}
