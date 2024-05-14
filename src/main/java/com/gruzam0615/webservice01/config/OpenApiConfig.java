package com.gruzam0615.webservice01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

// @OpenAPIDefinition(
//     info = @Info(
//         title = "Webservice01",
//         description = "Springboot Restful API example",
//         contact = @Contact(
//             name = "gruzam0615",
//             email = "gruzam0615@gmail.com",
//             url = ""
//         )
//     ),
//     servers = {
//         @Server(
//             url = "http://localhost:8080",
//             description = "DEV ENV"
//         )
//     },
//     security = {
//         @SecurityRequirement(
//             name = "Authorization"
//         )
//     }
// )
// @SecurityScheme(
//     name = "Authorization",
//     description = "JWT auth",
//     scheme = "Authorization",
//     type = SecuritySchemeType.APIKEY,
//     bearerFormat = "JWT",
//     in = SecuritySchemeIn.HEADER
// )

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        String jwt = "Authorization";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt);
        Components components = new Components()
            .addSecuritySchemes(jwt, new SecurityScheme()
                .name(jwt)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
            );

        return new OpenAPI()
            .components(new Components())
            .info(apiInfo())
            .addSecurityItem(securityRequirement)
            .components(components);
    }

    private Info apiInfo() {
        return new Info()
            .title("Webservice01")
            .description("Springboot Restful API example")
            .version("0.0.1")
            .contact(contactIn());
    }

    private Contact contactIn() {
        return new Contact()
            .name("gruzam0615")
            .email("gruzam0615@gmail.com")
            .url("");

    }

    private SecurityScheme scheme() {
        return new SecurityScheme()
            .name("Authorization")
            .description("JWT auth")
            .scheme("Authorization")
            .type(Type.APIKEY)
            .in(In.HEADER);
    }

}
