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
  @NonNull private AppRoleMapper appRoleMapper;

  @Override
  public AppRoleDto getAppRole(@NonNull final String id) {
    AppRole appRole = appRoleRepository.findById(id).orElse(null);
    return appRoleMapper.fromAppRole(appRole);
  }

  @Override
  public Map<GrantedAuthority, AppRole> getMappedAppRoles() {
    final Map<GrantedAuthority, AppRole> ret = new HashMap<>();
    final List<AppRole> appRoles = appRoleRepository.findAll();
    for (final AppRole appRole : appRoles) {
      ret.put(new SimpleGrantedAuthority(appRole.getId()), appRole);
    }
    return ret;
  }

  @Override
  public List<AppRoleDto> getAppRoles() {
    return appRoleMapper.appRoleListToAppRoleDtoList(appRoleRepository.findAll());
  }

  @Override
  public void saveOrUpdate(AppRoleDto appRoleDto) {
    AppRole appRole = appRoleRepository.findById(appRoleDto.getId()).orElse(new AppRole());
    appRoleMapper.updateDtoToAppRole(appRoleDto, appRole);
    appRoleRepository.save(appRole);
  }

  @Override
  public void delete(String id) {
    appRoleRepository.deleteById(id);
  }
}
