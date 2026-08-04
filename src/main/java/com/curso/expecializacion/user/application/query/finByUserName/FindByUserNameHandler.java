package com.curso.expecializacion.user.application.query.finByUserName;


import com.curso.expecializacion.product.common.mediator.RequestHandler;
import com.curso.expecializacion.user.domain.Usuario;
import com.curso.expecializacion.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindByUserNameHandler implements RequestHandler<FindByUserNameRequest, FindByUserNameResponse> {
    private final UserRepository userRepository;
    @Override
    public FindByUserNameResponse handle(FindByUserNameRequest request) {
        Usuario usuario = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el email: " + request.getUserName()));

        return new FindByUserNameResponse(usuario);
    }

    @Override
    public Class<FindByUserNameRequest> getRequesType() {
        return FindByUserNameRequest.class;
    }
}
