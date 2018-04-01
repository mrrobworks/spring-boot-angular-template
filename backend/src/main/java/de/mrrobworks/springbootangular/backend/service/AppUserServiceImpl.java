package de.mrrobworks.springbootangular.backend.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.mrrobworks.springbootangular.backend.domain.AppUser;
import de.mrrobworks.springbootangular.backend.repository.AppUserRepository;

@Service
public class AppUserServiceImpl implements AppUserService {

  @Autowired
  private AppUserRepository appUserRepository;

  @Override
  public Optional<AppUser> getByGoogleId(String id) {
    return appUserRepository.findById(id);
  }

  @Override
  public void createAppUser(AppUser appUser) {
    appUserRepository.save(appUser);
  }
}
