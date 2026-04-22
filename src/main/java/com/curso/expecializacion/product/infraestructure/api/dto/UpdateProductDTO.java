package com.curso.expecializacion.product.infraestructure.api.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@RequiredArgsConstructor
public class UpdateProductDTO {
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private Double precio;
    private MultipartFile file;
}
