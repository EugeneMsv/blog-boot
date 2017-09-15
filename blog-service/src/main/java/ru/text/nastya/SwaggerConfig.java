package ru.text.nastya;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Config for auto building documentation for mvc controllers.
 * Default path: /v2/api-docs, and /swagger-ui.html .propeties
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.text.nastya.web.controllers"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo()).useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Documentation for Blog API", "Blog API service",
                null, null, null, null, null, Collections.emptyList());
    }
}
