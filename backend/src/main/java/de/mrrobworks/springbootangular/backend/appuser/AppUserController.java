package de.mrrobworks.springbootangular.backend.appuser;

import de.mrrobworks.springbootangular.backend.global.CorsConfiguration;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static de.mrrobworks.springbootangular.backend.global.WebOAuth2ConfigHelper.getUserId;

@Slf4j
@RestController
@CorsConfiguration
@RequiredArgsConstructor
@RequestMapping(value = "/backend/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AppUserController {

  @NonNull private final AppUserService appUserService;
  @NonNull private final AppUserMapper appUserMapper;

  //  @GetMapping("/noauth")
  //  public ResponseEntity<?> noAuth() {
  //    Map<String, String> body = new HashMap<>();
  //    body.put("message", "unauthorized");
  //    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
  //  }

  // TODO:https://github.com/spring-projects/spring-security/blob/master/samples/boot/oauth2login/src/main/java/sample/web/OAuth2LoginController.java
  @GetMapping("/info")
  @ResponseBody
  public ResponseEntity<AppUserDto> user(@AuthenticationPrincipal OAuth2User oauth2User) {
    log.info("##### /backend/user/info " + oauth2User.getName());
    String userId = getUserId(oauth2User.getAttributes());
    AppUser appUser =
        appUserService.getAppUser(userId).orElseGet(() -> appUserService.createAppUser(userId));
    return ResponseEntity.ok(appUserMapper.fromAppUser(appUser));
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
