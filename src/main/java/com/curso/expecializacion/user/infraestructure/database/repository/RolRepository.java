package com.curso.expecializacion.user.infraestructure.database.repository;

import com.curso.expecializacion.user.infraestructure.database.entity.RolEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends CrudRepository<RolEntity,Integer> {

}
