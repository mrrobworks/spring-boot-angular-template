package de.mrrobworks.springbootangular.backend.person;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

  @NonNull private final PersonRepository personRepository;
  @NonNull private final PersonMapper personMapper;

  @Override
  public List<PersonDto> findAllPersons() {
    return personMapper.personListToPersonDtoList(personRepository.findAll());
  }
}
