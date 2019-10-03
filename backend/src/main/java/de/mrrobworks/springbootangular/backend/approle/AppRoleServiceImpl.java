package de.mrrobworks.springbootangular.backend.approle;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppRoleServiceImpl implements AppRoleService {

  @NonNull private final AppRoleRepository appRoleRepository;
  @NonNull private final AppRoleMapper appRoleMapper;

  @Override
  public AppRoleDto getAppRole(String id) {
    AppRole appRole = appRoleRepository.findByIdGet(id);
    return appRoleMapper.fromAppRole(appRole);
  }

  @Override
  public Map<GrantedAuthority, AppRole> getMappedAppRoles() {
    return appRoleRepository.findAll().stream()
        .collect(
            Collectors.toMap(
                appRole -> new SimpleGrantedAuthority(appRole.getId()), appRole -> appRole));
  }

  @Override
  public List<AppRoleDto> getAppRoles() {
    return appRoleMapper.appRoleListToAppRoleDtoList(appRoleRepository.findAll());
  }

  @Override
  public AppRoleDto createOrUpdateAppRole(AppRoleDto appRoleDto) {
    AppRole appRole = appRoleRepository.findById(appRoleDto.getId()).orElse(new AppRole());
    appRoleMapper.updateDtoToAppRole(appRoleDto, appRole);
    AppRole savedAppRole = appRoleRepository.save(appRole);
    return appRoleMapper.fromAppRole(savedAppRole);
  }

  @Override
  public void deleteAppRole(String id) {
    appRoleRepository.deleteById(id);
  }
}
