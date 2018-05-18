package de.mrrobworks.springbootangular.backend.configuration;

import de.mrrobworks.springbootangular.backend.domain.AppRole;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import de.mrrobworks.springbootangular.backend.domain.AppUser;
import de.mrrobworks.springbootangular.backend.service.AppUserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
final class GoogleAuthoritiesExtractor implements AuthoritiesExtractor {

  @NonNull
  private final AppUserService appUserService;

  @Override
  public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
    final String googleUserId = (String) map.get("sub");
    log.info("Extract authorities for {}", googleUserId);
    final Optional<AppUser> appUser = appUserService.getByGoogleId(googleUserId);
    if (!appUser.isPresent()) {
      return Collections.emptyList();
    }
    return AuthorityUtils.createAuthorityList(
        appUser.get().getRoles().stream().map(AppRole::getAuthority).toArray(String[]::new));
  }
}
