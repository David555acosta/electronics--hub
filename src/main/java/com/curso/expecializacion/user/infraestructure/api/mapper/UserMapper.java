package com.curso.expecializacion.user.infraestructure.api.mapper;


import com.curso.expecializacion.user.application.login.LoginUserRequest;
import com.curso.expecializacion.user.application.login.LoginUserResponse;
import com.curso.expecializacion.user.application.register.RegisterUserRequest;
import com.curso.expecializacion.user.application.register.RegisterUserResponse;
import com.curso.expecializacion.user.infraestructure.api.dto.LoginRequestDTO;
import com.curso.expecializacion.user.infraestructure.api.dto.RegisterRequestDTO;
import com.curso.expecializacion.user.infraestructure.api.dto.TokenResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    LoginUserRequest mapToLoginUserRequest(LoginRequestDTO loginRequestDto);

    RegisterUserRequest mapToRegisterUserRequest(RegisterRequestDTO registerRequestDto);

    TokenResponseDTO mapToTokenResponseDto(LoginUserResponse loginUserResponse);

    TokenResponseDTO mapToTokenResponseDto(RegisterUserResponse registerUserResponse);
}
