package de.mrrobworks.springbootangular.backend.configuration;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import de.mrrobworks.springbootangular.backend.repository.AppRoleRepository;
import de.mrrobworks.springbootangular.backend.repository.AppUserRepository;
import de.mrrobworks.springbootangular.backend.repository.PersonRepository;
import lombok.Getter;

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
 * 
 * 
 * @author robert
 */
@Getter
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DbProvider {

  @NonNull
  private PersonRepository personRepository;

  @NonNull
  private AppUserRepository appUserRepository;

  @NonNull
  private AppRoleRepository appRoleRepository;
}
