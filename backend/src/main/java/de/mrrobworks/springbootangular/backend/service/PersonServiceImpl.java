package de.mrrobworks.springbootangular.backend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.mrrobworks.springbootangular.backend.domain.Person;
import de.mrrobworks.springbootangular.backend.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  private PersonRepository repo;
  
  @Override
  public List<Person> findAllPersons() {
    
    return repo.findAll();
  }
}
