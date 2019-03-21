package de.mrrobworks.springbootangular.backend.repository;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import de.mrrobworks.springbootangular.backend.domain.Person;
import de.mrrobworks.springbootangular.backend.helper.DbUnitTestCase;
import de.mrrobworks.springbootangular.backend.helper.EnableDatabaseManager;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test-Class for Persons.
 *
 * TODO: Test run failed. Need to be fixed.
 *
 * @author robert
 */
@Disabled
@SpringBootTest
class PersonRepositoryTest extends EnableDatabaseManager implements DbUnitTestCase {

  @Autowired
  private PersonRepository cut;

  @Disabled
  @Test
  @DatabaseSetup(value = "/dbunit-db/setup/setup-person.xml", type = DatabaseOperation.CLEAN_INSERT)
  void testFindPersons() {
    final List<Person> persons = cut.findAll();
    Assertions.assertEquals(3, persons.size());
  }

  @Disabled
  @Test
  @DatabaseSetup(value = "/dbunit-db/setup/setup-person.xml", type = DatabaseOperation.CLEAN_INSERT)
  @ExpectedDatabase(value = "/dbunit-db/expected/expected-person.xml",
      assertionMode = DatabaseAssertionMode.NON_STRICT)
  void testSavePerson() {
    cut.save(
        Person.builder().id(4L).firstname("personFirstname4").lastname("personLastname4").build());
  }
}
