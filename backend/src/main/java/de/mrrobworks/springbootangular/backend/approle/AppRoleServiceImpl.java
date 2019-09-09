package de.mrrobworks.springbootangular.backend.approle;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppRoleServiceImpl implements AppRoleService {

  @NonNull private AppRoleRepository appRoleRepository;

  @Override
  public AppRole getAppRole(@NonNull final String id) {
    return appRoleRepository.findById(id).orElse(null);
  }

  @Override
  public Map<GrantedAuthority, AppRole> getMappedAppRoles() {
    final Map<GrantedAuthority, AppRole> ret = new HashMap<>();
    final List<AppRole> appRoles = appRoleRepository.findAll();
    for (final AppRole appRole : appRoles) {
      ret.put(new SimpleGrantedAuthority(appRole.getAuthority()), appRole);
    }
    return ret;
  }

  @Override
  public List<AppRole> getAppRoles() {
    return appRoleRepository.findAll();
  }

  @Override
  public void save(AppRole appRole) {
    appRoleRepository.save(appRole);
  }

  @Override
  public void delete(String id) {
    appRoleRepository.deleteById(id);
  }
}
