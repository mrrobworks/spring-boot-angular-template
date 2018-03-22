package de.mrrobworks.springbootangular.backend.configuration;

import org.springframework.stereotype.Component;
import de.mrrobworks.springbootangular.backend.repository.PersonRepository;
import lombok.Getter;

@Getter
@Component
public class DbProvider {

  private PersonRepository personRepository;
}
