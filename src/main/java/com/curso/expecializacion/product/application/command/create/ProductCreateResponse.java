package com.curso.expecializacion.product.application.command.create;

import com.curso.expecializacion.product.common.mediator.Request;
import com.curso.expecializacion.product.domain.Product;
import com.curso.expecializacion.product.infraestructure.api.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@AllArgsConstructor
@Data
public class ProductCreateResponse {
    private Product product;
}
