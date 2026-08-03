package com.curso.expecializacion.user.infraestructure.api;

import com.curso.expecializacion.user.domain.Usuario;
import com.curso.expecializacion.user.infraestructure.api.dto.LoginRequestDTO;
import com.curso.expecializacion.user.infraestructure.api.dto.RegisterRequestDTO;
import com.curso.expecializacion.user.infraestructure.api.dto.TokenResponseDTO;
import com.curso.expecializacion.user.infraestructure.api.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface User_Api {

    ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDto);


    ResponseEntity<TokenResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDto);


    ResponseEntity<Void> deleteByID(@PathVariable Integer id);


    ResponseEntity<UsuarioDTO> findByUserName(@PathVariable String email);

}
