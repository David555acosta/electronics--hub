package com.curso.expecializacion.user.application.register;


import com.curso.expecializacion.config.security.jwt.JwtUtils;
import com.curso.expecializacion.product.common.mediator.RequestHandler;
import com.curso.expecializacion.user.domain.Erol;
import com.curso.expecializacion.user.domain.Rol;
import com.curso.expecializacion.user.domain.Usuario;
import com.curso.expecializacion.user.domain.port.UserRepository;
import com.curso.expecializacion.user.infraestructure.database.entity.RolEntity;
import com.curso.expecializacion.user.infraestructure.database.mapper.UsuarioEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;




@Service
@RequiredArgsConstructor
public class RegisterUserHandler implements RequestHandler<RegisterUserRequest, RegisterUserResponse> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UsuarioEntityMapper usuarioEntityMapper;
    @Override
    public RegisterUserResponse handle(RegisterUserRequest request) {

        boolean existById = userRepository.existsByUsername(request.getUsername());

        if (existById) {
            throw new RuntimeException("El nombre de usuario ya se encuentra registrado");
        }


        Set<RolEntity> roles = request.getRole().stream()
                .map(rol -> RolEntity.builder()
                        .rol(Erol.valueOf(rol))
                        .build())
                .collect(Collectors.toSet());

        Set<Rol> rolsDomain = roles.stream()
                .map(usuarioEntityMapper::mapToRol)
                .collect(Collectors.toSet());

        String password = passwordEncoder.encode(request.getPassword());

        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(password)
                .rols(rolsDomain)
                .build();

        userRepository.upsert(usuario);

        //Generamos momentaneamente un token luego de registrarnos aquí

        String token = jwtUtils.generateAccessToken(usuario.getUsername());

        return new RegisterUserResponse(token);
    }

    @Override
    public Class<RegisterUserRequest> getRequesType() {
        return RegisterUserRequest.class;
    }
}
