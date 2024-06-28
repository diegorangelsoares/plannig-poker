package com.diego.planningpoker.infrastructure.config;

import com.diego.planningpoker.api.configuration.ApplicationProperty;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

	private static final String SCOPE = "global";
    private static final String HTTP_HEADER = "header";
    public static final String DEFAULT_INCLUDE_PATTERN = "/.*";

    private final ApplicationProperty appProperty;
    
    public SwaggerConfig(ApplicationProperty appProperty) {
		super();
		this.appProperty = appProperty;
	}

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("testes").pathsToMatch("/**").build();
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(info())
				.components(new Components().addSecuritySchemes("bearer-jwt", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
				.in(SecurityScheme.In.HEADER).name("Authorization")))
				.addSecurityItem(new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")));
	}
	
	private Info info() {
		return new Info().title(appProperty.getApiInfo().getTitulo())
				.description(appProperty.getApiInfo().getDescricao()).version(appProperty.getApiInfo().getVersao());
	}

}