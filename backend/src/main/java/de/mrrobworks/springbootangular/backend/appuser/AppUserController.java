package de.mrrobworks.springbootangular.backend.appuser;

import de.mrrobworks.springbootangular.backend.global.CorsConfiguration;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CorsConfiguration
@RequiredArgsConstructor
@RequestMapping(value = "/backend/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AppUserController {

  @NonNull private final AppUserService appUserService;
  @NonNull private final AppUserMapper appUserMapper;

  @RequestMapping("/info")
  public ResponseEntity<AppUserDto> user(@AuthenticationPrincipal AppUser user) {
    return ResponseEntity.ok(appUserMapper.fromAppUser(user));
  }

  @ApiOperation(value = "Get a list of application users.")
  @GetMapping("/list")
  public ResponseEntity<List<AppUserDto>> getAppUsers() {
    return ResponseEntity.ok(appUserService.getAllAppUsers());
  }

  @PutMapping("/{id}")
  public ResponseEntity<AppUserDto> updateUser(
      @RequestBody @Valid AppUserDto appUser, @PathVariable("id") String id, Errors errors) {
    return ResponseEntity.ok().body(appUserService.updateAppUser(appUser));
  }
}
