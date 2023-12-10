package com.springBootProject.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggeConfig {
	@Bean
	public OpenAPI api() {
		return new OpenAPI()
				.info(new Info().title("Employee Management APIs")
						.description("this is employee management system develped by Rahul Dubey").version("v1")
						.contact(new Contact().name("Rahul dubey").email("dubeyrahul7405@gmail.com"))
						.license(new License().name("Apache")))
				.externalDocs(new ExternalDocumentation().url("http://localhost:9090/swagger-ui/index.html"));

	}

//	@Bean
//	public SwaggerUiConfigParameters swaggerUiConfigParameters() {
//		return SwaggerUiConfigParametersBuilder.builder().docExpansion(DocExpansion.LIST).build();
//	}

}
