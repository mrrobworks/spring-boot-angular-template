package de.mrrobworks.springbootangular.backend.approle;

import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Map;

public interface AppRoleService {

  AppRole getAppRole(@NonNull final String id);

  Map<GrantedAuthority, AppRole> getMappedAppRoles();

  List<AppRole> getAppRoles();

  void save(AppRole appRole);

  void delete(String id);
}
