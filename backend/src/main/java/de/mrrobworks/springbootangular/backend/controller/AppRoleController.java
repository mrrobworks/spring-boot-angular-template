package de.mrrobworks.springbootangular.backend.controller;

import de.mrrobworks.springbootangular.backend.domain.AppRole;
import de.mrrobworks.springbootangular.backend.service.AppRoleService;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backend/role")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppRoleController implements CorsConfiguration {

  @NonNull
  private AppRoleService appRoleService;

  @GetMapping("/{id}")
  public AppRole getAppRole(@PathVariable String id) {
    return appRoleService.getAppRole(id);
  }

  @GetMapping(value = "/list")
  public List<AppRole> getAppRoles() {
    return appRoleService.getAppRoles();
  }
}
