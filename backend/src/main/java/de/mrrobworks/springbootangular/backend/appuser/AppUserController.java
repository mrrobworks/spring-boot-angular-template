package de.mrrobworks.springbootangular.backend.appuser;

import de.mrrobworks.springbootangular.backend.global.CorsConfiguration;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CorsConfiguration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/backend/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AppUserController {

  private final @NonNull AppUserService appUserService;

  @RequestMapping("/info")
  public ResponseEntity<AppUser> user(@AuthenticationPrincipal AppUser user) {
    return ResponseEntity.ok(user);
  }

  @ApiOperation(value = "Get a list of application users.")
  @GetMapping("/list")
  public ResponseEntity<List<AppUser>> getAppUsers() {
    return ResponseEntity.ok(appUserService.getAllAppUsers());
  }

  @PutMapping("/{id}")
  public void updateUser(
      @RequestBody @Valid AppUser appUser, @PathVariable("id") String id, Errors errors) {
    appUserService.save(appUser);
  }
}
