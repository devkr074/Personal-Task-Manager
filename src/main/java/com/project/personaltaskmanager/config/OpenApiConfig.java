package com.project.personaltaskmanager.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * Sets up OpenAPI definition for Swagger UI.
 */
@OpenAPIDefinition(
  info = @Info(
    title = "Personal Task Manager API",
    version = "v1",
    description = "CRUD operations for tasks, categories, and priorities"
  )
)
@Configuration
public class OpenApiConfig {
    // No additional code needed; annotations drive config
}
