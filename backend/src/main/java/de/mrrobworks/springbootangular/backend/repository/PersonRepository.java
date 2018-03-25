package de.mrrobworks.springbootangular.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.mrrobworks.springbootangular.backend.domain.Person;

/**
 * {@link JpaRepository} for a Person.
 * 
 * @author robert
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

}
