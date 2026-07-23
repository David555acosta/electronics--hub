package com.curso.expecializacion.user.domain;

import com.curso.expecializacion.user.infraestructure.database.UsuarioEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findByUsername(String username);

    @Query("select u from UsuarioEntity u where u.username = ?1")
    Optional<UsuarioEntity> getName(String username );
}
