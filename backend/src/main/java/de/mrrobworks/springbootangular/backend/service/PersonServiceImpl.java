package de.mrrobworks.springbootangular.backend.service;

import de.mrrobworks.springbootangular.backend.domain.Person;
import de.mrrobworks.springbootangular.backend.repository.PersonRepository;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonServiceImpl implements PersonService {

  @NonNull
  private PersonRepository personRepository;

  @Override
  public List<Person> findAllPersons() {
    return personRepository.findAll();
  }
}
