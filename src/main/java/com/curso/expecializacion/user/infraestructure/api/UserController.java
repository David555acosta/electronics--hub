package com.curso.expecializacion.user.infraestructure.api;

import com.curso.expecializacion.product.common.mediator.Mediator;
import com.curso.expecializacion.user.application.login.LoginUserRequest;
import com.curso.expecializacion.user.application.login.LoginUserResponse;
import com.curso.expecializacion.user.application.register.RegisterUserRequest;
import com.curso.expecializacion.user.application.register.RegisterUserResponse;
import com.curso.expecializacion.user.infraestructure.api.dto.LoginRequestDTO;
import com.curso.expecializacion.user.infraestructure.api.dto.RegisterRequestDTO;
import com.curso.expecializacion.user.infraestructure.api.dto.TokenResponseDTO;
import com.curso.expecializacion.user.infraestructure.api.mapper.UserMapper;
import com.curso.expecializacion.user.infraestructure.database.repository.UsuarioRepository;
import com.curso.expecializacion.user.domain.Erol;
import com.curso.expecializacion.user.infraestructure.database.entity.RolEntity;
import com.curso.expecializacion.user.infraestructure.database.entity.UsuarioEntity;
import com.curso.expecializacion.user.infraestructure.api.dto.CreateUserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final Mediator mediator;
    private final UserMapper userMapper;

    //@Autowired
    //private PasswordEncoder passwordEncoder;

    //@Autowired
    //private UsuarioRepository usuarioRepository;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


    @GetMapping("/helloSecured")
    public String helloSecured() {
        return "hello con seguridad";
    }

    /*@PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDto) {

        LoginUserRequest request = userMapper.mapToLoginUserRequest(loginRequestDto);

        LoginUserResponse response = mediator.dispacth(request);

        TokenResponseDTO tokenResponseDto = userMapper.mapToTokenResponseDto(response);

        return ResponseEntity.ok(tokenResponseDto);
    }*/

    @PostMapping("/create")
    public ResponseEntity<TokenResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDto) {

        RegisterUserRequest request = userMapper.mapToRegisterUserRequest(registerRequestDto);

        RegisterUserResponse response = mediator.dispacth(request);

        TokenResponseDTO tokenResponseDto = userMapper.mapToTokenResponseDto(response);

        return ResponseEntity.ok(tokenResponseDto);
    }



   /* @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioEntity> deleteUser(@PathVariable Integer id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }*/

}
