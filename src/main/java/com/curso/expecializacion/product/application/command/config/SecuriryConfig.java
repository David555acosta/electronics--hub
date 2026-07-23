package com.curso.expecializacion.product.application.command.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecuriryConfig {
    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/index.html", "/swagger-ui/**", "/v3/api-docs/**")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form.successHandler(authenticationSuccessHandler()).permitAll())
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                                .sessionFixation().migrateSession().invalidSessionUrl("/login")
                                .maximumSessions(1) //maximas sesiones
                                .expiredUrl("/login") //donde va ser redirigido el usuario si expira la sesion
                                .sessionRegistry(sessionRegistry()) //rastro de los datos de sesion
                )
                .build();
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // 2. OBLIGATORIO en APIs REST: Desactivar CSRF para permitir peticiones POST
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        // Documentación pública
                        .requestMatchers("/swagger-ui/index.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // 3. OBLIGATORIO: Permitir la creación de usuarios de forma pública
                        .requestMatchers(HttpMethod.POST, "/user/create").permitAll()
                        .requestMatchers("/user/hello").permitAll()

                        // Rutas protegidas que exigen estar autenticado
                        .requestMatchers("/user/helloSecured").authenticated()

                        // Todo lo demás exige autenticación
                        .anyRequest().authenticated()
                )
                // Usamos HTTP Basic Authentication para enviar usuario/password en el Header
                .httpBasic(Customizer.withDefaults())
                .build();
    }


    //bean para que spring detecte un nuevo inicio de sesion
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    //Lugar a donde voy a ser redirigido luego de ser autenticado en localhost//9626
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            response.sendRedirect("/productos/v1/session");
        };
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

}
