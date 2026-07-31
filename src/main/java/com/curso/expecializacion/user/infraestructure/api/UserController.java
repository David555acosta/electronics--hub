package com.curso.expecializacion.user.infraestructure.api;

import com.curso.expecializacion.product.common.mediator.Mediator;
import com.curso.expecializacion.user.application.login.LoginUserRequest;
import com.curso.expecializacion.user.application.login.LoginUserResponse;
import com.curso.expecializacion.user.application.register.RegisterUserRequest;
import com.curso.expecializacion.user.application.register.RegisterUserResponse;
import com.curso.expecializacion.user.domain.port.UserRepository;
import com.curso.expecializacion.user.infraestructure.api.dto.LoginRequestDTO;
import com.curso.expecializacion.user.infraestructure.api.dto.RegisterRequestDTO;
import com.curso.expecializacion.user.infraestructure.api.dto.TokenResponseDTO;
import com.curso.expecializacion.user.infraestructure.api.mapper.UserMapper;
import com.curso.expecializacion.user.infraestructure.database.entity.UsuarioEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RequestMapping("/user")
@Tag(name = "Users", description = "Endpoints para la gestión y autenticación de usuarios")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final Mediator mediator;
    private final UserMapper userMapper;
    private final UserRepository userRepository;



    @Operation(summary = "Endpoint público de prueba")
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @Operation(summary = "Endpoint de prueba protegido", security = @SecurityRequirement(name = "Bearer Authentication"))
    @GetMapping("/helloSecured")
    public String helloSecured() {
        return "hello con seguridad";
    }

    @Operation(summary = "Iniciar sesión para obtener el Token JWT")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDto) {
        LoginUserRequest request = userMapper.mapToLoginUserRequest(loginRequestDto);
        LoginUserResponse response = mediator.dispacth(request);
        TokenResponseDTO tokenResponseDto = userMapper.mapToTokenResponseDto(response);
        return ResponseEntity.ok(tokenResponseDto);
    }

    @Operation(summary = "Registrar un nuevo usuario")
    @PostMapping("/create")
    public ResponseEntity<TokenResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDto) {
        RegisterUserRequest request = userMapper.mapToRegisterUserRequest(registerRequestDto);
        RegisterUserResponse response = mediator.dispacth(request);
        TokenResponseDTO tokenResponseDto = userMapper.mapToTokenResponseDto(response);
        return ResponseEntity.ok(tokenResponseDto);
    }

    @Operation(summary = "Eliminar un usuario por ID (Requiere ROL ADMIN)", security = @SecurityRequirement(name = "Bearer Authentication"))
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioEntity> deleteB(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
