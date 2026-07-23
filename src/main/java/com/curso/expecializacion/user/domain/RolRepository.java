package com.curso.expecializacion.user.domain;

import com.curso.expecializacion.user.infraestructure.database.RolEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends CrudRepository<RolEntity,Integer> {

}
