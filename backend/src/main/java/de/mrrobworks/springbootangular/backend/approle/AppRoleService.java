package de.mrrobworks.springbootangular.backend.approle;

import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Map;

public interface AppRoleService {

  AppRoleDto getAppRole(@NonNull final String id);

  Map<GrantedAuthority, AppRole> getMappedAppRoles();

  List<AppRoleDto> getAppRoles();

  void saveOrUpdate(AppRoleDto appRole);

  void delete(String id);
}
