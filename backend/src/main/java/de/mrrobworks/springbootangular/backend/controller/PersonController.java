package de.mrrobworks.springbootangular.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import de.mrrobworks.springbootangular.backend.domain.Person;
import de.mrrobworks.springbootangular.backend.service.PersonService;

@RestController
@RequestMapping("/springbootangular")
public class PersonController implements CorsConfiguration {

  @Autowired
  private PersonService service;

  @RequestMapping(value = "/findallpersons", method = RequestMethod.GET)
  public List<Person> findAllPersons() {
    return service.findAllPersons();
  }
}
