package de.mrrobworks.springbootangular.backend.controller;

import de.mrrobworks.springbootangular.backend.domain.AppUser;
import de.mrrobworks.springbootangular.backend.service.AppUserService;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backend/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppUserController implements CorsConfiguration  {

  @NonNull
  private AppUserService appUserService;

  @RequestMapping("/info")
  public AppUser user(@AuthenticationPrincipal AppUser user) {
    return user;
  }

  @GetMapping("/list")
  public List<AppUser> getAppUsers() {
    return appUserService.getAllAppUsers();
  }

  // @RequestMapping("/info")
  // public Principal user(Principal user) {
  // return user;
  // }
}
