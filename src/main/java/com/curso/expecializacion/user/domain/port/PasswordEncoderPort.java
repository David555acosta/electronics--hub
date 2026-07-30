package com.curso.expecializacion.user.domain.port;

import org.springframework.stereotype.Repository;

@Repository
public interface PasswordEncoderPort {
    String encode(String password);
}
