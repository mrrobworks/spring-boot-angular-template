package de.mrrobworks.springbootangular.backend.service;

import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import de.mrrobworks.springbootangular.backend.domain.AppRole;

public interface AppRoleService {

  Map<GrantedAuthority, AppRole> getAppRoles();
}
