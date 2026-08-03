package com.curso.expecializacion.user.infraestructure.api.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RolDTO {
    private Integer id;
    private String rol;
}
