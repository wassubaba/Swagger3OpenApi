package com.openapi.definition.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
				
				title = "User Image Control API",
				description = "Doing CRUD Operations",
				summary = "This User-Image-API doing the Simpe CRUD Operations",
				termsOfService = "T&C",
				contact = @Contact(name = "wassubaba", email = "wassubaba@gmail.com"),
				license = @License(name = "License Number"),
				version = "v1"
					),
		servers = {
				@Server(description = "Dev", url = "http://localhost:8080"),
				@Server(description = "Test", url = "http://localhost:8080")
				}
		
)
	
public class OpenApiConfig {

}
