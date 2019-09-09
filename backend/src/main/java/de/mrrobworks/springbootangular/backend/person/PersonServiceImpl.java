package de.mrrobworks.springbootangular.backend.person;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonServiceImpl implements PersonService {

  @NonNull private PersonRepository personRepository;

  @Override
  public List<Person> findAllPersons() {
    return personRepository.findAll();
  }
}
