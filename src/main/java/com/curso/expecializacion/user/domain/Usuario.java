package com.curso.expecializacion.user.domain;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class Usuario {

    private Integer id;
    private String username;
    private String nickname;
    private String email;
    private String password;
    private Set<Rol> rols;


    public void actualizUsuario(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }
}
