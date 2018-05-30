package de.mrrobworks.springbootangular.backend.controller;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.mrrobworks.springbootangular.backend.domain.AppUser;

@RestController
@RequestMapping("/backend/user")
public class AppUserController implements CorsConfiguration  {

  @RequestMapping("/info")
  public AppUser user(@AuthenticationPrincipal AppUser user) {
    return user;
  }


  // @RequestMapping("/info")
  // public Principal user(Principal user) {
  // return user;
  // }
}
