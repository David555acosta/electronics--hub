package com.curso.expecializacion.product.application.command.update;

import com.curso.expecializacion.product.common.mediator.Request;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class UpdateProductCreateRequest implements Request<Void> {
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private Double precio;
    private MultipartFile file;
}
