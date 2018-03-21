package de.mrrobworks.springbootangular.backend.repository;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import de.mrrobworks.springbootangular.backend.domain.Person;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PersonRepositoryTest {

  @Autowired
  private PersonRepository cut;

  @Test
  void testFindPersons() throws Exception {
    final List<Person> persons = cut.findAll();
    Assertions.assertEquals(2, persons.size());
  }
}
