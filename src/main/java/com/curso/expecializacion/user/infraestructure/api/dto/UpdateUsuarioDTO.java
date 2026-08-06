package com.curso.expecializacion.user.infraestructure.api.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UpdateUsuarioDTO {

    private Integer id;
    private String nickname;
    private String email;
}
