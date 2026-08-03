package com.curso.expecializacion.user.infraestructure.database.mapper;


import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;
import com.curso.expecializacion.user.domain.Rol;
import com.curso.expecializacion.user.domain.Usuario;
import com.curso.expecializacion.user.infraestructure.api.dto.RolDTO;
import com.curso.expecializacion.user.infraestructure.api.dto.UsuarioDTO;
import com.curso.expecializacion.user.infraestructure.database.entity.UsuarioEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UsuarioEntityMapper {

    UsuarioEntity mapToUserEntity(Usuario usuario);

    Usuario mapToUser(UsuarioEntity usuarioEntity);


    UsuarioDTO mapToUserDTO(Usuario usuario);

    Usuario mapToUser(UsuarioDTO usuarioDTO);

    RolDTO mapToRolDTO(Rol rol);

    Rol mapToRol(RolDTO rolDTO);


}
