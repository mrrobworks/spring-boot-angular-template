package de.mrrobworks.springbootangular.backend.approle;

import de.mrrobworks.springbootangular.backend.global.CorsConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CorsConfiguration
@RequiredArgsConstructor
@RequestMapping(value = "/backend/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AppRoleController {

  @NonNull private final AppRoleService appRoleService;

  @GetMapping("/{id}")
  public ResponseEntity<AppRoleDto> getAppRole(@PathVariable String id) {
    return ResponseEntity.ok().body(appRoleService.getAppRole(id));
  }

  @GetMapping("/list")
  public ResponseEntity<List<AppRoleDto>> getAppRoles() {
    return ResponseEntity.ok().body(appRoleService.getAppRoles());
  }

  @PutMapping("/{id}")
  public ResponseEntity<AppRoleDto> updateRole(
      @RequestBody @Valid AppRoleDto appRole, @PathVariable("id") String id, Errors errors) {
    return ResponseEntity.ok().body(appRoleService.createOrUpdateAppRole(appRole));
  }

  @PostMapping("/add")
  public ResponseEntity<AppRoleDto> addRole(@RequestBody @Valid AppRoleDto appRole, Errors errors) {
    return ResponseEntity.ok().body(appRoleService.createOrUpdateAppRole(appRole));
  }

  @DeleteMapping("/{id}")
  public void deleteRole(@PathVariable("id") String id) {
    appRoleService.deleteAppRole(id);
  }
}
