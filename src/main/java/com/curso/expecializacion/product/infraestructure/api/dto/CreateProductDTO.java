package com.curso.expecializacion.product.infraestructure.api.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

@Data
@RequiredArgsConstructor
public class CreateProductDTO {
    private Integer codigo;
    @NotBlank
    private String nombre;
    @Length(min = 2, max = 100, message = "No se puede ingresar un valor menor a 2 caracteres o mayor a 100")
    private String descripcion;
    @DecimalMin(value = "0.00", inclusive = false, message = "No podes ingresar un numero menor a 0.00 crack")
    @DecimalMax(value = "10000.00", inclusive = false, message = " no se puede ingresar un numero mayor a 10000.0")
    private Double precio;
    private MultipartFile file;
}
