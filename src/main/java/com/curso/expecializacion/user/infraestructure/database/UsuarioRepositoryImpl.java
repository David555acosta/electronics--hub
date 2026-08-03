package com.curso.expecializacion.user.infraestructure.database;
import com.curso.expecializacion.user.domain.Usuario;
import com.curso.expecializacion.user.domain.port.UserRepository;
import com.curso.expecializacion.user.infraestructure.database.entity.UsuarioEntity;
import com.curso.expecializacion.user.infraestructure.database.mapper.UsuarioEntityMapper;
import com.curso.expecializacion.user.infraestructure.database.repository.QueryUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UserRepository {

    private final QueryUserRepository queryUserRepository;
    private final UsuarioEntityMapper usuarioEntityMapper;

    @Override
    public boolean existsByEmail(String email) {
        return queryUserRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean existsByUsername(String username) {
        return queryUserRepository.findByUsername(username).isPresent();
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return queryUserRepository.findByEmail(email).map(usuarioEntityMapper::mapToUser);
    }

    @Override
    public Usuario upsert(Usuario usuario) {
        UsuarioEntity userEntity = usuarioEntityMapper.mapToUserEntity(usuario);
        UsuarioEntity saved = queryUserRepository.save(userEntity);
        return usuarioEntityMapper.mapToUser(saved);
    }

    @Override
    public void deleteById(Integer id) {
       queryUserRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> findByUserName(String username) {
        return queryUserRepository.findByUsername(username).map(usuarioEntityMapper::mapToUser);
    }
}
