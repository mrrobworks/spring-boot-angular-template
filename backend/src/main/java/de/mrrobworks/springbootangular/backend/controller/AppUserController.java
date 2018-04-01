package de.mrrobworks.springbootangular.backend.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.mrrobworks.springbootangular.backend.domain.AppUser;

@RestController
@RequestMapping("/backend/user")
public class AppUserController {

  @RequestMapping("/info")
  public AppUser user(@AuthenticationPrincipal AppUser user) {
    return user;
  }

  // @RequestMapping("/info")
  // public Principal user(Principal user) {
  // return user;
  // }
}
