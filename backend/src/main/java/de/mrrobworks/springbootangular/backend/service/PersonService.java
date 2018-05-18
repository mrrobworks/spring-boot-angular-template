package de.mrrobworks.springbootangular.backend.service;

import java.util.List;
import org.springframework.stereotype.Service;
import de.mrrobworks.springbootangular.backend.domain.Person;

/**
 * Service-Interface for Persons.
 * 
 * @author robert
 */
@Service
public interface PersonService {

  List<Person> findAllPersons();
}
