package com.example.pokemonproject.config.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import static org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        String securitySchemeName = "BearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("Pokémon Project API")
                        .description("REST API documentation with JWT Bearer token authentication."))

                .addServersItem(new Server()
                        .url("http://localhost:8080")
                        .description("Local development server"))

                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))

                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))

                        .addParameters(
                                ACCEPT_LANGUAGE,
                                new Parameter()
                                        .name("Accept-Language")
                                        .in("header")
                                        .required(false)
                                        .description("Response language (en, ar)")
                                        .schema(
                                                new StringSchema()
                                                        ._default("en")
                                                        .addEnumItem("en")
                                                        .addEnumItem("ar")
                                        )
                        )
                );
    }

    @Bean
    public OperationCustomizer addGlobalHeaders() {
        return (Operation operation, HandlerMethod handlerMethod) -> {

            operation.addParametersItem(
                    new Parameter()
                            .$ref("#/components/parameters/" + ACCEPT_LANGUAGE)
            );

            return operation;
        };
    }
}
