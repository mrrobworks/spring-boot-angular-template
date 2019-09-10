package de.mrrobworks.springbootangular.backend.appuser;

import java.util.List;
import java.util.Optional;

public interface AppUserService {

  Optional<AppUser> getByUserId(String id);

  AppUser createAppUser(String userId);

  List<AppUserDto> getAllAppUsers();

  AppUserDto updateAppUser(AppUserDto appUser);
}
