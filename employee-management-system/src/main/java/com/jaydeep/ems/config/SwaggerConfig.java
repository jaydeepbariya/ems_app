package com.jaydeep.ems.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	private static final String AUTHORIZATION_HEADER = "Authentication";
	
	private List<SecurityContext> securityContexts(){
		return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build()); 
	}
	
	private List<SecurityReference> sf() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		
		return Arrays.asList(new SecurityReference("scope", new AuthorizationScope[]{authorizationScope}));
	}

	
	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}
	
	@Bean
	public Docket apis() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				
				.securityContexts(securityContexts())
				.securitySchemes(List.of(apiKey()))
				
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	@SuppressWarnings("deprecation")
	private ApiInfo getInfo() {
		
		return new ApiInfo("Employee Management System", "Backend Manage Employee Data", 
				"1.0", "Terms Of Service", "Jaydeep Bariya", "Private License", "mailto:jaydeepbariya59@gmail.com");
	}
}
