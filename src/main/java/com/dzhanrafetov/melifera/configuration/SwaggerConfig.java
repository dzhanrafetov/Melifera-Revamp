package com.dzhanrafetov.melifera.configuration;

import org.elasticsearch.common.util.set.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet("application/json", "application/xml", "application/x-yaml"))

                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dzhanrafetov.melifera.controller")) // Replace with your controller package
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .globalRequestParameters(parameters());

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Your API Documentation")
                .description("API documentation for your Spring Boot application")
                .version("1.0")
                .contact(new Contact("Dzhan Rafetov", "", "dzhanrafetov@gmail.com"))
                .build();

    }
    private List<RequestParameter> parameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("Authorization")
                .description("JWT Token")
                .in(ParameterType.HEADER)
                .required(false)
                .build());
        return parameters;
    }
}