package de.mrrobworks.springbootangular.backend.appuser;

import de.mrrobworks.springbootangular.backend.approle.AppRole;
import de.mrrobworks.springbootangular.backend.approle.AppRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    AppUser appUser =
            appUserRepository.findById(appUserDto.getId()).orElseThrow(EntityNotFoundException::new);
    appUserMapper.updateDtoToAppUser(appUserDto, appUser);
    AppUser savedAppUser = appUserRepository.save(appUser);
    return appUserMapper.fromAppUser(savedAppUser);
  }

  @Override
  public Optional<AppUser> getAppUser(String userId) {
    return appUserRepository.findById(userId);
  }

  @Override
  public AppUser createAppUser(String userId) {
    Map<GrantedAuthority, AppRole> appRoles = appRoleService.getMappedAppRoles();
    // For Test reasons: New users get the Role "ROLE_ADMIN" instead of "ROLE_USER"
    List<AppRole> appUserRoles =
        AuthorityUtils.createAuthorityList("ROLE_ADMIN").stream()
            .map(appRoles::get)
            .collect(Collectors.toList());
    return appUserRepository.save(AppUser.builder().id(userId).roles(appUserRoles).build());
  }
}
