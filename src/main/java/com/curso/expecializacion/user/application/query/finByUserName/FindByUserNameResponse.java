package com.curso.expecializacion.user.application.query.finByUserName;

import com.curso.expecializacion.user.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindByUserNameResponse {
    private Usuario usuario;
}
