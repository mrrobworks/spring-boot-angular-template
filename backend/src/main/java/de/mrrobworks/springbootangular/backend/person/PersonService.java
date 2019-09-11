package de.mrrobworks.springbootangular.backend.person;

import java.util.List;

public interface PersonService {

  List<PersonDto> findAllPersons();
}
