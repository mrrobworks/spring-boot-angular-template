package de.mrrobworks.springbootangular.backend.person;

import de.mrrobworks.springbootangular.backend.global.CorsConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CorsConfiguration
@RequestMapping("/backend/person")
@RequiredArgsConstructor
public class PersonController {

  @NonNull private final PersonService personService;

  @GetMapping(value = "/findallpersons")
  public ResponseEntity<List<PersonDto>> findAllPersons() {
    var auth = SecurityContextHolder.getContext().getAuthentication();
    log.info("Auth-name: {}", auth.getName());
    log.info("Auth-authorities: {}", auth.getAuthorities());
    return ResponseEntity.ok(personService.findAllPersons());
  }
}
