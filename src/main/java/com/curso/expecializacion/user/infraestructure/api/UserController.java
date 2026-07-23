package com.curso.expecializacion.user.infraestructure.api;

import com.curso.expecializacion.user.domain.UsuarioRepository;
import com.curso.expecializacion.user.infraestructure.api.dto.CreateUserDTO;
import com.curso.expecializacion.user.infraestructure.database.Erol;
import com.curso.expecializacion.user.infraestructure.database.RolEntity;
import com.curso.expecializacion.user.infraestructure.database.UsuarioEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }


    @GetMapping("/helloSecured")
    public String helloSecured(){
        return "hello con seguridad";
    }

    @PostMapping("")
    public ResponseEntity<UsuarioEntity> createUser(@Valid @RequestBody CreateUserDTO createUserDTO){

        Set<RolEntity> roles = createUserDTO.getRole().stream()
                .map(rol -> RolEntity.builder()
                        .rol(Erol.valueOf(rol))
                        .build())
                .collect(Collectors.toSet());

       UsuarioEntity usuarioEntity = UsuarioEntity.builder()
               .username(createUserDTO.getUsername())
               .email(createUserDTO.getEmail())
               .password(createUserDTO.getPassword())
               .rols(roles)
               .build();

       return ResponseEntity.ok(usuarioEntity);
    }
}
