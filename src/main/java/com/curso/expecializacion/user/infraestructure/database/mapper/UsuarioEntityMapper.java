package com.curso.expecializacion.user.infraestructure.database.mapper;


import com.curso.expecializacion.user.domain.Usuario;
import com.curso.expecializacion.user.infraestructure.database.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UsuarioEntityMapper {

    UsuarioEntity mapToUserEntity(Usuario usuario);

    UsuarioEntity mapToUser(UsuarioEntity usuarioEntity);
}
