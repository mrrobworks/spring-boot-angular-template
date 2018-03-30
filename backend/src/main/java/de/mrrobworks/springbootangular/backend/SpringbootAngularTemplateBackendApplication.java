package de.mrrobworks.springbootangular.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Main-Class for the Spring-Boot-Application.
 * 
 * @author robert
 */
@SpringBootApplication
//@EntityScan("de.mrrobworks.springbootangular.backend.domain")
public class SpringbootAngularTemplateBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootAngularTemplateBackendApplication.class, args);
  }
}
