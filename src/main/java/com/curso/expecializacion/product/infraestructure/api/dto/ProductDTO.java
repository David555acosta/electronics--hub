package com.curso.expecializacion.product.infraestructure.api.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class ProductDTO {
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;
}
