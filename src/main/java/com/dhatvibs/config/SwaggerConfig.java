package com.dhatvibs.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI deliveryPartnerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Delivery Partner API")
                        .description("APIs for Delivery Partner Application")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Backend Team")
                                .email("backend@company.com")));
    }
}
