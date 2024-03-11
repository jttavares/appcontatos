package com.tavares.appcontatos._0_configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .components(
                    new Components().addSecuritySchemes("bearerAuth", 
                    new SecurityScheme().type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWt")))
                .info(new Info()
                    .title("API Rest para Controle de cadastro de Pessoas e Contatos")
                    .description("Esta api faz controle de cadastro de Pessoas e seus contatos")
                    .contact(new Contact()
                    .name("Tavares")
                    .email("jttavaresg@gmail.com")
                    .url("http://localhost")
                    )
                    .version("Versão 1.0.0-SNAPSHOT")  
                                 
                    )
                    .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    } 
}
