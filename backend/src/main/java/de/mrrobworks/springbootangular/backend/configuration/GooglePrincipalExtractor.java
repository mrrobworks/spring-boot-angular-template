package de.mrrobworks.springbootangular.backend.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import de.mrrobworks.springbootangular.backend.domain.AppRole;
import de.mrrobworks.springbootangular.backend.domain.AppUser;
import de.mrrobworks.springbootangular.backend.service.AppRoleService;
import de.mrrobworks.springbootangular.backend.service.AppUserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
final class GooglePrincipalExtractor implements PrincipalExtractor {

  @Autowired
  private AppUserService appUserService;

  @Autowired
  private AppRoleService appRoleService;

  @Override
  public Object extractPrincipal(Map<String, Object> map) {
    final String googleUserId = (String) map.get("sub");
    log.info("Extract Principal for {}", googleUserId);
    final Optional<AppUser> appUser = appUserService.getByGoogleId(googleUserId);
    if (appUser.isPresent()) {
      return appUser.get();
    }
    return createAppUser(googleUserId);
  }

  private AppUser createAppUser(final String googleUserId) {
    final AppUser appUser = new AppUser();
    appUser.setId(googleUserId);

    final List<AppRole> appUserRoles = new ArrayList<>();
    final Map<GrantedAuthority, AppRole> appRoles = appRoleService.getAppRoles();
    final List<GrantedAuthority> authorities = AppUserAuthorityUtils.createAuthorities(appUser);
    for (final GrantedAuthority grantedAuthority : authorities) {
      appUserRoles.add(appRoles.get(grantedAuthority));
    }
    appUser.setRoles(appUserRoles);

    // final Authentication authentication =
    // SecurityContextHolder.getContext().getAuthentication();
    // final OAuth2AuthenticationDetails details =
    // ((OAuth2AuthenticationDetails) authentication.getDetails());
    // // Token for interact with Google
    // String token = ((OAuth2AuthenticationDetails)authentication.getDetails()).getTokenValue();
    appUserService.createAppUser(appUser);
    return appUser;
  }
}
