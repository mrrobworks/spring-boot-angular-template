package de.mrrobworks.springbootangular.backend.global;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(
            RequestHandlerSelectors.basePackage(
                "de.mrrobworks.springbootangular.backend.controller"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(metaData());
  }

  private ApiInfo metaData() {
    return new ApiInfoBuilder()
        .title("spring-boot-angular-template REST-API")
        .description("REST-Endpoint for spring-boot-angular-template")
        .contact(
            new Contact("mrrobworks", "https://github.com/mrrobworks", "mrrobworks@github.com"))
        .build();
  }
}
