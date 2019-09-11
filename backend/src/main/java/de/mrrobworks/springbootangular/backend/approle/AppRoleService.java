package de.mrrobworks.springbootangular.backend.approle;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Map;

public interface AppRoleService {

  AppRoleDto getAppRole(String id);

  Map<GrantedAuthority, AppRole> getMappedAppRoles();

  List<AppRoleDto> getAppRoles();

  AppRoleDto createOrUpdateAppRole(AppRoleDto appRole);

  void deleteAppRole(String id);
}
