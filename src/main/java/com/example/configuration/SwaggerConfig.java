package com.example.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(title = "API de clubes",
        description = "Utilizando configuraciones de Swagger en API",
        version = "1.0.0",
        contact = @Contact(name = "Edinson Cotrina Ramos")),
        servers = {
                @Server(
                        description = "Dev demo Server",
                        url = "http://localhost:8080"
                )
        },
        security = {
                @SecurityRequirement( name = "Security Token") //nombre del securityScheme definido en Swagger (lineas abajo)
        }
)
//SecurityScheme es la anotacion importante para el uso de tokens con swagger cuando se usa Spring Security
//En SecurityScheme hay un dato que es type, al no tener default es obligatorio
@SecurityScheme(
        name = "Security Token", //nombre del securityScheme definido en Swagger
        description = "Acceso con Token para las APIS",
        type = SecuritySchemeType.HTTP, //HTTP usado para tokens
        paramName = HttpHeaders.AUTHORIZATION, //Header donde se obtiene el token
        in = SecuritySchemeIn.HEADER, //La ubicacion donde se encuentra el token
        scheme = "bearer", //schema de auntentication
        bearerFormat = "JWT" //tipo formato bearer
)
public class SwaggerConfig {
}
