package de.mrrobworks.springbootangular.backend.service;

import java.util.List;
import org.springframework.stereotype.Service;
import de.mrrobworks.springbootangular.backend.domain.Person;

@Service
public interface PersonService {
  
  public List<Person> findAllPersons(); 
}
