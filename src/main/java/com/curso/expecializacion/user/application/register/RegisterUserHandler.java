package com.curso.expecializacion.user.application.register;


import com.curso.expecializacion.config.security.jwt.JwtUtils;
import com.curso.expecializacion.product.common.mediator.RequestHandler;
import com.curso.expecializacion.user.domain.Erol;
import com.curso.expecializacion.user.domain.Usuario;
import com.curso.expecializacion.user.infraestructure.database.UsuarioRepositoryImpl;
import com.curso.expecializacion.user.infraestructure.database.entity.RolEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;




@Service
@RequiredArgsConstructor
public class RegisterUserHandler implements RequestHandler<RegisterUserRequest, RegisterUserResponse> {

    private final UsuarioRepositoryImpl usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    @Override
    public RegisterUserResponse handle(RegisterUserRequest request) {

        boolean existById = usuarioRepository.existsByUsername(request.getUsername());

        if (existById) {
            throw new RuntimeException("Usuario no encontrado");
        }

        String password = passwordEncoder.encode(request.getPassword());


        Set<RolEntity> roles = request.getRole().stream()
                .map(rol -> RolEntity.builder()
                        .rol(Erol.valueOf(rol))
                        .build())
                .collect(Collectors.toSet());

        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rols(roles)
                .build();

        usuarioRepository.upsert(usuario);

        //Generamos momentaneamente un token luego de registrarnos aquí

        String token = jwtUtils.generateAccessToken(usuario.getUsername());

        return new RegisterUserResponse(token);
    }

    @Override
    public Class<RegisterUserRequest> getRequesType() {
        return RegisterUserRequest.class;
    }
}
