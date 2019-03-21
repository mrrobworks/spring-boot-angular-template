package de.mrrobworks.springbootangular.backend.service;

import de.mrrobworks.springbootangular.backend.domain.Person;
import java.util.List;

public interface PersonService {

  List<Person> findAllPersons();
}
