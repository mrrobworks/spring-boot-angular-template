package de.mrrobworks.springbootangular.backend.controller;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import de.mrrobworks.springbootangular.backend.domain.Person;
import de.mrrobworks.springbootangular.backend.service.PersonService;
import lombok.extern.slf4j.Slf4j;

/**
 * REST-Controller for {@link PersonService}.
 * 
 * @author robert
 */
@Slf4j
@RestController
@RequestMapping("/backend/person")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController implements CorsConfiguration {

  @NonNull
  private PersonService service;

  @RequestMapping(value = "/findallpersons", method = RequestMethod.GET)
  public List<Person> findAllPersons() {
    final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    log.info("Auth-name: {}", auth.getName());
    log.info("Auth-authorities: {}", auth.getAuthorities());
    return service.findAllPersons();
  }
}
