package com.curso.expecializacion;

import com.curso.expecializacion.user.infraestructure.database.repository.UsuarioRepository;
import com.curso.expecializacion.user.domain.Erol;
import com.curso.expecializacion.user.infraestructure.database.entity.RolEntity;
import com.curso.expecializacion.user.infraestructure.database.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class ExpecializacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpecializacionApplication.class, args);
	}

}
