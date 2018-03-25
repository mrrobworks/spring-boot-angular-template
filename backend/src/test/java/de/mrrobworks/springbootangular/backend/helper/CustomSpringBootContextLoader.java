package de.mrrobworks.springbootangular.backend.helper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootContextLoader;

/**
 * Context-Loader to avoid a {@link java.awt.HeadlessException} when starting the
 * {@link org.hsqldb.util.DatabaseManagerSwing} in a Spring-Boot-Context.
 *
 * @see DbUnitTestCase
 * @author robert
 */
public class CustomSpringBootContextLoader extends SpringBootContextLoader {

  @Override
  protected SpringApplication getSpringApplication() {
    return new SpringApplicationBuilder().headless(false).build();
  }
}
