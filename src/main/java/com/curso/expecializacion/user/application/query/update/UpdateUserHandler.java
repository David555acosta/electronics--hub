package com.curso.expecializacion.user.application.query.update;


import com.curso.expecializacion.product.common.mediator.RequestHandler;
import com.curso.expecializacion.user.domain.Usuario;
import com.curso.expecializacion.user.domain.port.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UpdateUserHandler implements RequestHandler<UpdateUserRequest, Void> {

    private final UserRepository userRepository;

    @Override
    public Void handle(UpdateUserRequest request) {

        Usuario usuario = userRepository.findById(request.getId())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        usuario.actualizUsuario(request.getNickname() , request.getEmail());
        userRepository.update(usuario) ;
        log.info("Usuario , PRODUCT UPDATE HANDLER , Username:{}", request.getNickname());
        return null;
    }

    @Override
    public Class<UpdateUserRequest> getRequesType() {
        return UpdateUserRequest.class;
    }
}
