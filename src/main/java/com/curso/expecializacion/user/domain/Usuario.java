package com.curso.expecializacion.user.domain;
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
    private Set<Rol> rols;
}
