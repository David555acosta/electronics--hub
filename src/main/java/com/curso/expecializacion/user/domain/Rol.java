package com.curso.expecializacion.user.domain;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rol {

    private Integer id;
    private Erol rol;
}
