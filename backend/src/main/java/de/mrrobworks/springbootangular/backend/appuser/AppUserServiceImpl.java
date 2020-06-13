package de.mrrobworks.springbootangular.backend.appuser;

import de.mrrobworks.springbootangular.backend.approle.AppRole;
import de.mrrobworks.springbootangular.backend.approle.AppRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

  @NonNull private final AppRoleService appRoleService;
  @NonNull private final AppUserRepository appUserRepository;
  @NonNull private final AppUserMapper appUserMapper;

  @Override
  public List<AppUserDto> getAllAppUsers() {
    List<AppUser> appUsers = appUserRepository.findAll();
    return appUserMapper.appUserListToAppUserDtoList(appUsers);
  }

  @Override
  public AppUserDto updateAppUser(AppUserDto appUserDto) {
    AppUser appUser = appUserRepository.findByIdGet(appUserDto.getId());
    appUserMapper.updateDtoToAppUser(appUserDto, appUser);
    AppUser savedAppUser = appUserRepository.save(appUser);
    return appUserMapper.fromAppUser(savedAppUser);
  }

  @Override
  public Optional<AppUser> getAppUser(String userId) {
    Optional<AppUser> byId = appUserRepository.findById(userId);
    return byId;
  }

  @Override
  public AppUser createAppUser(String userId) {
    Map<GrantedAuthority, AppRole> mappedAppRoles = appRoleService.getMappedAppRoles();
    // For Test reasons: New users get the Role "ROLE_ADMIN" instead of "ROLE_USER"
    return appUserRepository.save(
        AppUser.builder()
            .id(userId)
            .roles(
                AuthorityUtils.createAuthorityList("ROLE_ADMIN").stream()
                    .map(mappedAppRoles::get)
                    .collect(Collectors.toList()))
            .build());
  }
}
