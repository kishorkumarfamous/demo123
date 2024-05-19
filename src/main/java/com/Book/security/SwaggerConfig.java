package com.Book.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Configuration
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER="Authorization";

    private ApiKey apiKey(){
        return new ApiKey("JWT","AUTHORIZATION_HEADER","header");
    }


    private List<SecurityContext>securityContexts(){
        return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
    }
    private List<SecurityReference>sf(){

        AuthorizationScope scope=new AuthorizationScope("global","accessEverything");
        return Arrays.asList( new SecurityReference("JWT",new AuthorizationScope[]{scope}));

    }
    @Bean
    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .securityContexts(securityContexts())
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors
                        .any()).
                paths(PathSelectors.
                        any()).
                build();
    }

    private ApiInfo getInfo(){
        return new ApiInfo(" Demo Project for Wishfy","this project is developed by kishor kumar",
                "1.0","term of service",new Contact("kishor",
                "https://kishork644","kishork644@gmail.com"),"Licence of Api's",
                "Api License Url", Collections.emptyList());
    }
}
