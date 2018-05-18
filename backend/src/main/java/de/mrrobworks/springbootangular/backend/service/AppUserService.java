package de.mrrobworks.springbootangular.backend.service;

import java.util.Optional;
import de.mrrobworks.springbootangular.backend.domain.AppUser;

public interface AppUserService {

  Optional<AppUser> getByGoogleId(String id);
  
  void createAppUser(final AppUser appUser);
}
