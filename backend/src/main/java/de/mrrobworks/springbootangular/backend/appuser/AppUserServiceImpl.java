package de.mrrobworks.springbootangular.backend.appuser;

import de.mrrobworks.springbootangular.backend.approle.AppRole;
import de.mrrobworks.springbootangular.backend.approle.AppRoleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppUserServiceImpl implements AppUserService {

  @NonNull private AppRoleService appRoleService;
  @NonNull private AppUserRepository appUserRepository;
  @NonNull private AppUserMapper appUserMapper;

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
  public AppUser createAppUser(final String userId) {
    final Map<GrantedAuthority, AppRole> appRoles = appRoleService.getMappedAppRoles();
    // For Test reasons: New users get the Role "ROLE_ADMIN" instead of "ROLE_USER"
    List<AppRole> appUserRoles =
        AuthorityUtils.createAuthorityList("ROLE_ADMIN").stream()
            .map(appRoles::get)
            .collect(Collectors.toList());
    return appUserRepository.save(AppUser.builder().id(userId).roles(appUserRoles).build());
  }
}
