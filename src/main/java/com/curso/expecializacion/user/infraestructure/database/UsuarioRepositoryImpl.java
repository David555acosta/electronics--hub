package com.curso.expecializacion.user.infraestructure.database;

import com.curso.expecializacion.user.domain.Usuario;
import com.curso.expecializacion.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UserRepository {

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public Usuario upsert(Usuario user) {
        return null;
    }
}
