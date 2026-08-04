package com.curso.expecializacion.user.infraestructure.database.mapper;

import com.curso.expecializacion.user.domain.Erol;
import com.curso.expecializacion.user.domain.Rol;
import com.curso.expecializacion.user.domain.Usuario;
import com.curso.expecializacion.user.infraestructure.api.dto.RolDTO;
import com.curso.expecializacion.user.infraestructure.api.dto.UsuarioDTO;
import com.curso.expecializacion.user.infraestructure.database.entity.RolEntity;
import com.curso.expecializacion.user.infraestructure.database.entity.UsuarioEntity;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UsuarioEntityMapper {

    UsuarioEntity mapToUserEntity(Usuario usuario);

    Usuario mapToUser(UsuarioEntity usuarioEntity);


    /////////////////////////////////////////


    @Mapping(target = "password", ignore = true)
    UsuarioDTO mapToUserDTO(Usuario usuario);

    @Mapping(target = "rols", ignore = true)
    Usuario mapToUser(UsuarioDTO usuarioDTO);

    ////////////////////////////////////////////

    @Mapping(target = "id", ignore = true)
    Rol mapToRol(RolEntity rolEntity);

    @Mapping(target = "id", ignore = true)
    RolEntity mapToRolEntity(Rol rol);


    ////////////////////////////////////////////

    RolDTO mapToRolDTO(Rol rol);

    Rol mapToRol(RolDTO rol);


    /////////////////////////////


    default String mapRolToString(RolDTO rolDTO) {
        return rolDTO != null ? rolDTO.getRol() : null;
    }

    default Erol mapStringToErol(String rol) {
        return rol != null ? Erol.valueOf(rol) : null;
    }
}
