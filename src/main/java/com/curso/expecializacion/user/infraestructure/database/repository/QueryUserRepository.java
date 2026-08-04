package com.curso.expecializacion.user.infraestructure.database.repository;
import com.curso.expecializacion.user.infraestructure.database.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QueryUserRepository extends JpaRepository<UsuarioEntity, Integer> {
    Optional<UsuarioEntity> findByEmail(String email);

    void deleteByid(Integer id);

    @EntityGraph(attributePaths = {"rols"})
    Optional<UsuarioEntity> findByUsername(String username);

}
