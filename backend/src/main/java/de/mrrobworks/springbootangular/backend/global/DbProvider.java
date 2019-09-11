package de.mrrobworks.springbootangular.backend.global;

import de.mrrobworks.springbootangular.backend.approle.AppRoleRepository;
import de.mrrobworks.springbootangular.backend.appuser.AppUserRepository;
import de.mrrobworks.springbootangular.backend.person.PersonRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Class provides all {@link JpaRepository} from the {@code backend}-Module.
 *
 * <pre>
 *  Usage:
 *  {@code
 *      &#64;Autowired
 *      private DbProvider dbProvider;
 *      ...
 *      dbProvider.getXXXRepository}
 * </pre>
 */
@Getter
@Component
@RequiredArgsConstructor
public class DbProvider {

  private final PersonRepository personRepository;
  private final AppUserRepository appUserRepository;
  private final AppRoleRepository appRoleRepository;
}
