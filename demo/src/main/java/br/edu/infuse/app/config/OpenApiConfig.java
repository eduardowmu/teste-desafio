package br.edu.infuse.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Desafio teste")
                        .description("Desafio para vaga, criação e consulta de Pedidos de cliente")
                        .version("1.0.0"));
    }
}