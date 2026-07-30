package com.curso.expecializacion.user.domain.port;


public interface AuthenticationPort {

    String authenticate(String username, String password);

}
