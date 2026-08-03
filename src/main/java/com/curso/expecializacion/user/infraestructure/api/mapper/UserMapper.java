package com.curso.expecializacion.user.infraestructure.api.mapper;


import com.curso.expecializacion.product.infraestructure.database.entity.ProductEntity;
import com.curso.expecializacion.user.application.login.LoginUserRequest;
import com.curso.expecializacion.user.application.login.LoginUserResponse;
import com.curso.expecializacion.user.application.register.RegisterUserRequest;
import com.curso.expecializacion.user.application.register.RegisterUserResponse;
import com.curso.expecializacion.user.domain.Rol;
import com.curso.expecializacion.user.domain.Usuario;
import com.curso.expecializacion.user.infraestructure.api.dto.*;
import com.curso.expecializacion.user.infraestructure.database.entity.UsuarioEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    LoginUserRequest mapToLoginUserRequest(LoginRequestDTO loginRequestDto);

    RegisterUserRequest mapToRegisterUserRequest(RegisterRequestDTO registerRequestDto);

    TokenResponseDTO mapToTokenResponseDto(LoginUserResponse loginUserResponse);

    TokenResponseDTO mapToTokenResponseDto(RegisterUserResponse registerUserResponse);


}
