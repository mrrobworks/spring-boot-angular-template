package de.mrrobworks.springbootangular.backend.repository;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import de.mrrobworks.springbootangular.backend.domain.Person;
import de.mrrobworks.springbootangular.backend.helper.DbUnitTestCase;
import de.mrrobworks.springbootangular.backend.helper.EnableDatabaseManager;

/**
 * Test-Class for Persons.
 * 
 * @author robert
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PersonRepositoryTest extends EnableDatabaseManager implements DbUnitTestCase {

  @Autowired
  private PersonRepository cut;

  @Test
  @DatabaseSetup(value = "/dbunit-db/setup/setup-person.xml", type = DatabaseOperation.CLEAN_INSERT)
  void testFindPersons() throws Exception {
    final List<Person> persons = cut.findAll();
    Assertions.assertEquals(3, persons.size());
  }

  @Test
  @DatabaseSetup(value = "/dbunit-db/setup/setup-person.xml", type = DatabaseOperation.CLEAN_INSERT)
  @ExpectedDatabase(value = "/dbunit-db/expected/expected-person.xml",
      assertionMode = DatabaseAssertionMode.NON_STRICT)
  void testSavePerson() throws Exception {
    cut.save(
        Person.builder().id(4L).firstname("personFirstname4").lastname("personLastname4").build());
  }
}
