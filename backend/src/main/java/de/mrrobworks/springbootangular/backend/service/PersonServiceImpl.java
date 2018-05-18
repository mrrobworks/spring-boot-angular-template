package de.mrrobworks.springbootangular.backend.service;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.mrrobworks.springbootangular.backend.domain.Person;
import de.mrrobworks.springbootangular.backend.repository.PersonRepository;

/**
 * Service-Implementation for Persons.
 * 
 * @author robert
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonServiceImpl implements PersonService {

  @NonNull
  private PersonRepository repo;

  @Override
  public List<Person> findAllPersons() {
    return repo.findAll();
  }
}
