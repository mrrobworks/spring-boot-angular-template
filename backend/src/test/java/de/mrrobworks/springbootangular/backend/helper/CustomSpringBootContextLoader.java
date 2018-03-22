package de.mrrobworks.springbootangular.backend.helper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootContextLoader;

public class CustomSpringBootContextLoader extends SpringBootContextLoader {

  @Override
  protected SpringApplication getSpringApplication() {
    return new SpringApplicationBuilder().headless(false).build();
  }
}
