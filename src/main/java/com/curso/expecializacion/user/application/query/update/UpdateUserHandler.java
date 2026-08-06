package com.curso.expecializacion.user.application.query.update;


import com.curso.expecializacion.product.common.mediator.RequestHandler;
import com.curso.expecializacion.user.domain.Usuario;
import com.curso.expecializacion.user.domain.port.UserRepository;
import com.curso.expecializacion.user.infraestructure.database.mapper.UsuarioEntityMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UpdateUserHandler implements RequestHandler<UpdateUserRequest, Void> {

    private final UserRepository userRepository;
    private String username;

    @Override
    public Void handle(UpdateUserRequest request) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        /////////////////////////////////////////////////////

        if (principal instanceof UserDetails) {
             username = ((UserDetails) principal).getUsername();
        } else {
            throw new AccessDeniedException("Usuario no permitido");
        }


        ///////////////////////////////////////////////////

        boolean isAdmin = ((UserDetails) principal).getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("ADMIN"));



        Usuario usuarioAEditar = userRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        if (!isAdmin && !usuarioAEditar.getUsername().equals(username)) {
            throw new AccessDeniedException("No tienes permisos para modificar este usuario.");
        }

        usuarioAEditar.actualizUsuario(request.getNickname(), request.getEmail());
        userRepository.upsert(usuarioAEditar);
        return null;
    }

    @Override
    public Class<UpdateUserRequest> getRequesType() {
        return UpdateUserRequest.class;
    }
}
