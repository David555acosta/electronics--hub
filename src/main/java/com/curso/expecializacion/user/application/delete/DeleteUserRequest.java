package com.curso.expecializacion.user.application.delete;

import com.curso.expecializacion.product.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteUserRequest implements Request<Void> {
    private Integer id;
}
