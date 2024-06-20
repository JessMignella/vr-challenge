package br.com.vr.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("VR Autorizador API")
                        .description("API para autorizar transações do cartões de beneficio")
                        .contact(new Contact()
                                .name("Jessica Mignella")
                                .email("jeferigat@gmail.com"))
                        .version("0.0.1")
                );
    }

}
