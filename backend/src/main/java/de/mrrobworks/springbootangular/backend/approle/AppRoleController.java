package de.mrrobworks.springbootangular.backend.approle;

import de.mrrobworks.springbootangular.backend.global.CorsConfiguration;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CorsConfiguration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/backend/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AppRoleController {

  private final @NonNull AppRoleService appRoleService;

  @GetMapping("/{id}")
  public ResponseEntity<AppRoleDto> getAppRole(@PathVariable String id) {
    return ResponseEntity.ok().body(appRoleService.getAppRole(id));
  }

  @GetMapping("/list")
  public ResponseEntity<List<AppRoleDto>> getAppRoles() {
    return ResponseEntity.ok().body(appRoleService.getAppRoles());
  }

  @PutMapping("/{id}")
  public void updateRole(
      @RequestBody @Valid AppRoleDto appRole, @PathVariable("id") String id, Errors errors) {
    appRoleService.saveOrUpdate(appRole);
  }

  @PostMapping("/add")
  public void addRole(@RequestBody @Valid AppRoleDto appRole, Errors errors) {
    appRoleService.saveOrUpdate(appRole);
  }

  @DeleteMapping("/{id}")
  public void deleteRole(@PathVariable("id") String id) {
    this.appRoleService.delete(id);
  }
}
