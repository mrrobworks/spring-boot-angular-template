package de.mrrobworks.springbootangular.backend.service;

import java.util.List;
import java.util.Map;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import de.mrrobworks.springbootangular.backend.domain.AppRole;

public interface AppRoleService {

  AppRole getAppRole(@NonNull final String id);
  Map<GrantedAuthority, AppRole> getMappedAppRoles();
  List<AppRole> getAppRoles();
  void save(AppRole appRole);
}
