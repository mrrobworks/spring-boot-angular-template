package de.mrrobworks.springbootangular.backend.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import de.mrrobworks.springbootangular.backend.domain.Person;
import de.mrrobworks.springbootangular.backend.service.PersonService;

/**
 * REST-Controller for {@link PersonService}.
 * 
 * @author robert
 */
@RestController
@RequestMapping("/backend/person")
public class PersonController implements CorsConfiguration {

  @Autowired
  private PersonService service;

  @RequestMapping("/user")
  public Principal user(Principal user) {
    return user;
  }

  @RequestMapping(value = "/findallpersons", method = RequestMethod.GET)
  public List<Person> findAllPersons() {
    return service.findAllPersons();
  }
}
