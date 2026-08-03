package com.curso.expecializacion.user.application.delete;


import com.curso.expecializacion.product.common.mediator.RequestHandler;
import com.curso.expecializacion.user.domain.port.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Data
@RequiredArgsConstructor
@Slf4j
@Service
public class DeleteUserHandler implements RequestHandler<DeleteUserRequest,Void> {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public Void handle(DeleteUserRequest request) {
        log.info("Iniciando el proceso de delete de usuario de usuario con id".concat(request.getId().toString()));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        }

        userRepository.deleteById(request.getId());

        log.info("producto ELIMINADO , PRODUCT DELETE HANDLER , Codigo:{}", request.getId());
        return null;
    }

    @Override
    public Class<DeleteUserRequest> getRequesType() {
        return DeleteUserRequest.class;
    }
}

