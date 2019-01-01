package de.mrrobworks.springbootangular.backend.controller;

import de.mrrobworks.springbootangular.backend.domain.AppUser;
import de.mrrobworks.springbootangular.backend.service.AppUserService;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backend/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(
    value = "AppUserController REST Endpoint",
    description = "Contains CRUD Operations for AppUser-Objects.")
public class AppUserController implements CorsConfiguration {

  @NonNull private AppUserService appUserService;

  @RequestMapping("/info")
  public AppUser user(@AuthenticationPrincipal AppUser user) {
    return user;
  }

  @ApiOperation(value = "Get a list of application users.")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successful getting the application users."),
        @ApiResponse(
            code = 401,
            message =
                "Unauthorized. Login with suitable roles is required to get the application users.")
      })
  @GetMapping("/list")
  public List<AppUser> getAppUsers() {
    return appUserService.getAllAppUsers();
  }

  @PutMapping("/{id}")
  public void updateUser(@RequestBody AppUser appUser, @PathVariable("id") String id) {
    appUserService.save(appUser);
  }
}
