package com.curso.expecializacion.product.infraestructure.api.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

@Data
@RequiredArgsConstructor
public class UpdateProductDTO {
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private Double precio;
    @Size(max = 1024000)
    private MultipartFile file;
}
