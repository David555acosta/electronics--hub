package com.curso.expecializacion.user.domain;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rol {

    private Integer id;
    private String rol;


    public String getRol() {
        return rol;
    }
}
