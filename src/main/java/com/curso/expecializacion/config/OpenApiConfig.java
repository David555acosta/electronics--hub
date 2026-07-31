package com.curso.expecializacion.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "API Specialization",
                version = "1.0",
                description = "Documentation de la API del site de products",
                contact = @Contact(
                        name = "David",
                        email = "davidisrraelacostaz.9@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://springdoc.org"
                )
        ),
        servers = {
                @Server(
                        description = "Environment local",
                        url = "http://localhost:9528"
                )
        },
        tags = {
                @Tag(name = "Products", description = "operaciones relational a products"),
                @Tag(name = "Users", description = "Operaciones relacionadas a usuarios")
        }
)

@SecurityScheme(
        name = "Bearer Authentication",
        description = "Authentication with JWT",
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.DEFAULT,
        type = SecuritySchemeType.HTTP
)

@Configuration
public class OpenApiConfig {
}
