package aa.userservice.com.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Account Management and User Management API")
                        .version("v1")
                        .description("API for user management and account operations. Supports user registration, editing, deleting and data retrieving.")
                );
    }
}