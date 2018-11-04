package de.mrrobworks.springbootangular.backend.controller;

import de.mrrobworks.springbootangular.backend.domain.Person;
import de.mrrobworks.springbootangular.backend.service.PersonService;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-Controller for {@link PersonService}.
 *
 * @author robert
 */
// TODO: Replace JPA-Entity Person with a PersonViewModel
@Slf4j
@RestController
@RequestMapping("/backend/person")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController implements CorsConfiguration {

  @NonNull
  private PersonService service;

  @GetMapping(value = "/findallpersons")
  public List<Person> findAllPersons() {
    final var auth = SecurityContextHolder.getContext().getAuthentication();
    log.info("Auth-name: {}", auth.getName());
    log.info("Auth-authorities: {}", auth.getAuthorities());
    return service.findAllPersons();
  }
}
