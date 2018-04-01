package de.mrrobworks.springbootangular.backend.service;

import java.util.Optional;
import de.mrrobworks.springbootangular.backend.domain.AppUser;

public interface AppUserService {

  public Optional<AppUser> getByGoogleId(String id);
  
  public void createAppUser(final AppUser appUser);
}
