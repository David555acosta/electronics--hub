package com.curso.expecializacion.product.infraestructure.api.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class ProductDTO {
    private Integer codigo;
    @NotBlank
    private String nombre;
    @Length(min = 2, max = 100 , message = "No se puede ingresar un valor menor a 2 caracteres o mayor a 100")
    private String descripcion;
    @DecimalMin(value = "0.00" , inclusive = false)
    @DecimalMax(value = "10000.00" , inclusive = false)
    private Double precio;
    private String imagen;
}
