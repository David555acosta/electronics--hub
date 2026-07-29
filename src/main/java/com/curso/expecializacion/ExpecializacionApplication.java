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

	@Autowired
    PasswordEncoder passwordEncoder;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository) {
		return args -> {
			UsuarioEntity  usuario = UsuarioEntity.builder()
					.email("david@gmail.com")
					.username("david")
					.password(passwordEncoder.encode("1234"))
					.rols(Set.of(RolEntity.builder().rol(Erol.USER).build()))
					.build();



			UsuarioEntity  usuario2 = UsuarioEntity.builder()
					.email("pepe@gmail.com")
					.username("pepe")
					.password(passwordEncoder.encode("1234"))
					.rols(Set.of(RolEntity.builder().rol(Erol.USER).build()))
					.build();




			UsuarioEntity  usuario3 = UsuarioEntity.builder()
					.email("jose@gmail.com")
					.username("jose")
					.password(passwordEncoder.encode("1234"))
					.rols(Set.of(RolEntity.builder().rol(Erol.INVITED).build()))
					.build();


			usuarioRepository.save(usuario);
			usuarioRepository.save(usuario2);
			usuarioRepository.save(usuario3);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ExpecializacionApplication.class, args);
	}

}
