package com.curso.expecializacion.user.infraestructure.api;

import com.curso.expecializacion.product.infraestructure.api.dto.UpdateProductDTO;
import com.curso.expecializacion.user.domain.Usuario;
import com.curso.expecializacion.user.infraestructure.api.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface User_Api {

    ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDto);


    ResponseEntity<TokenResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDto);


    ResponseEntity<Void> deleteByID(@PathVariable Integer id);


    ResponseEntity<UsuarioDTO> findByUserName(@PathVariable String email);


    ResponseEntity<Void> update(@RequestBody @Valid UpdateUsuarioDTO updateUsuarioDTO);

}
