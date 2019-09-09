package de.mrrobworks.springbootangular.backend.appuser;

import java.util.List;
import java.util.Optional;

public interface AppUserService {

  Optional<AppUser> getByUserId(String id);

  void createAppUser(final AppUser appUser);

  List<AppUser> getAllAppUsers();

  void save(AppUser appUser);
}
