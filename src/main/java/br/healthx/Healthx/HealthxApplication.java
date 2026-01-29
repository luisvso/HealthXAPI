package br.healthx.Healthx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@OpenAPIDefinition(info = @Info(title = "Psychologist sessions managment API", version = "1.0", description = "API documentation for managment of psychologist sessions"))
@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class HealthxApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthxApplication.class, args);
    }

}
