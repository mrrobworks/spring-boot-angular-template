package de.mrrobworks.springbootangular.backend.controller;

import de.mrrobworks.springbootangular.backend.domain.AppRole;
import de.mrrobworks.springbootangular.backend.service.AppRoleService;
import java.util.List;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CorsConfiguration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/backend/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AppRoleController {

  @NonNull
  private AppRoleService appRoleService;

  @GetMapping("/{id}")
  public ResponseEntity<AppRole> getAppRole(@PathVariable String id) {
    return ResponseEntity.ok().body(appRoleService.getAppRole(id));
  }

  @GetMapping("/list")
  public ResponseEntity<List<AppRole>> getAppRoles() {
    return ResponseEntity.ok().body(appRoleService.getAppRoles());
  }

  @PutMapping("/{id}")
  public void updateRole(@RequestBody @Valid AppRole appRole, @PathVariable("id") String id,
      Errors errors) {
    appRoleService.save(appRole);
  }

  @PostMapping("/add")
  public void addRole(@RequestBody @Valid AppRole appRole, Errors errors) {
    appRoleService.save(appRole);
  }

  @DeleteMapping("/{id}")
  public void deleteRole(@PathVariable("id") String id) {
    this.appRoleService.delete(id);
  }
}
