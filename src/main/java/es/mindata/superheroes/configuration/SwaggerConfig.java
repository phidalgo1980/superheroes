package es.mindata.superheroes.configuration;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase de Configuracion de Swagger
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	//Pattern de apis que se deben autorizar
    public static final String DEFAULT_INCLUDE_PATTERN = "/superhero/.*";
    
    
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("es.mindata.superheroes.rest"))
				.paths(PathSelectors.any())
				.build().apiInfo(metaData())
	            .securityContexts(Lists.newArrayList(securityContext()))
	            .securitySchemes(Lists.newArrayList(apiKey()))
				.pathMapping("/");
	            //.globalOperationParameters(globalParameterList());
	}
	
    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
            .build();
    }
    
    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
    }
    
//    private List<Parameter> globalParameterList() {
//
//        Parameter authTokenHeader =
//            new ParameterBuilder()
//                .name("Accept-Language") // name of the header
//                .modelRef(new ModelRef("string")) // data-type of the header
//                .required(true) // required/optional
//                .parameterType("header") // for query-param, this value can be 'query'
//                .description("Accepted Language: en or es")
//                .defaultValue("en")
//                .build();
//
//        return Collections.singletonList(authTokenHeader);
//      }
    
	private ApiInfo metaData() {
	    ApiInfo apiInfo = new ApiInfo(
	            "Superheroes Rest API",
	            "Spring Boot REST API for Posts management",
	            "1.0",
	            "Terms of service",
	            new Contact("Pablo Hidalgo", "https://www.linkedin.com/in/pablo-hidalgo-b158a6b/", "phidalgo@gmail.com"),
	            "Apache License Version 2.0",
	            "https://www.apache.org/licenses/LICENSE-2.0",new ArrayList<VendorExtension>());
	    return apiInfo;
	}
}
