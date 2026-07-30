package com.curso.expecializacion.user.infraestructure.database.repository;

import com.curso.expecializacion.user.infraestructure.database.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryUserRepository extends JpaRepository<UsuarioEntity, Long> {

}
