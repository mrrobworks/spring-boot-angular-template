package de.mrrobworks.springbootangular.backend.repository;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import de.mrrobworks.springbootangular.backend.helper.DbUnitTestCase;
import de.mrrobworks.springbootangular.backend.helper.EnableDatabaseManager;
import de.mrrobworks.springbootangular.backend.person.Person;
import de.mrrobworks.springbootangular.backend.person.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@DbUnitTestCase
@SpringBootTest
class PersonRepositoryTest extends EnableDatabaseManager {

  @Autowired private PersonRepository cut;

  @Test
  @DatabaseSetup(value = "/dbunit-db/setup/setup-person.xml", type = DatabaseOperation.CLEAN_INSERT)
  void testFindPersons() {
    final List<Person> persons = cut.findAll();
    Assertions.assertEquals(3, persons.size());
  }

  @Test
  @DatabaseSetup(value = "/dbunit-db/setup/setup-person.xml", type = DatabaseOperation.CLEAN_INSERT)
  @ExpectedDatabase(
      value = "/dbunit-db/expected/expected-person.xml",
      assertionMode = DatabaseAssertionMode.NON_STRICT)
  void testSavePerson() {
    cut.save(
        Person.builder().id(4L).firstname("personFirstname4").lastname("personLastname4").build());
  }
}
