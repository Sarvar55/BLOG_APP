package com.example.blog.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * @project: Blog
 * @author: Sarvar55
 */
@Configuration
public class SwaggerConfig {

    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo()).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo getInfo() {
        return new ApiInfo("Blogging Application ",
                "Spring boot", "1.0", "Terms of Service",
                new Contact("Sarvar", "https://sarvar55.github.io/sarvar/", "servermusazade@gmail.com"),
                "License of APIS", "API license URL", Collections.emptyList());
    }
}
