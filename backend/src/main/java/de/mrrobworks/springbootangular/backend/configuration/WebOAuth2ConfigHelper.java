package de.mrrobworks.springbootangular.backend.configuration;

import de.mrrobworks.springbootangular.backend.domain.AppRole;
import de.mrrobworks.springbootangular.backend.domain.AppUser;
import de.mrrobworks.springbootangular.backend.service.AppRoleService;
import de.mrrobworks.springbootangular.backend.service.AppUserService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Getter
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebOAuth2ConfigHelper {

  @NonNull
  private WebOAuth2AuthoritiesExtractor webOAuth2AuthoritiesExtractor;

  @NonNull
  private WebOAuth2PrincipalExtractor webOAuth2PrincipalExtractor;

  @NonNull
  private WebOAuth2AuthenticationSuccessHandler webOAuth2AuthSuccessHandler;

  // TODO: throw BadCredentialsException if login failed
  private static String getUserId(Map<String, Object> map) {
    final HttpServletRequest currentRequest = ((ServletRequestAttributes) RequestContextHolder
        .currentRequestAttributes()).getRequest();
    final String requestURI = currentRequest.getRequestURI();
    String userId = null;
    if (requestURI.equals(WebOAuth2Config.GOOGLE_LOGIN_URL)) {
      userId = String.valueOf(map.get("sub"));
    } else if (requestURI.equals(WebOAuth2Config.GITHUB_LOGIN_URL)) {
      userId = String.valueOf(map.get("id"));
    }
    return userId;
  }

  @Component
  @RequiredArgsConstructor(onConstructor = @__(@Autowired))
  private static class WebOAuth2AuthoritiesExtractor implements AuthoritiesExtractor {

    @NonNull
    private final AppUserService appUserService;

    @Override
    public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
      final String userId = getUserId(map);
      log.info("Extract authorities for {}", userId);
      final Optional<AppUser> appUser = appUserService.getByUserId(userId);
      if (appUser.isEmpty()) {
        return Collections.emptyList();
      }
      return AuthorityUtils.createAuthorityList(
          appUser.get().getRoles().stream().map(AppRole::getAuthority).toArray(String[]::new));
    }
  }

  @Component
  @RequiredArgsConstructor(onConstructor = @__(@Autowired))
  private static class WebOAuth2PrincipalExtractor implements PrincipalExtractor {

    @NonNull
    private final AppUserService appUserService;

    @NonNull
    private final AppRoleService appRoleService;

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
      final String userId = getUserId(map);
      log.info("Extract Principal for {}", userId);
      final Optional<AppUser> appUser = appUserService.getByUserId(userId);
      return appUser.orElseGet(() -> createAppUser(userId));
    }

    private AppUser createAppUser(final String userId) {
      final AppUser appUser = new AppUser();
      appUser.setId(userId);
      final List<AppRole> appUserRoles = new ArrayList<>();
      final Map<GrantedAuthority, AppRole> appRoles = appRoleService.getMappedAppRoles();
      final List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
      for (final GrantedAuthority grantedAuthority : authorities) {
        appUserRoles.add(appRoles.get(grantedAuthority));
      }
      appUser.setRoles(appUserRoles);

      appUserService.createAppUser(appUser);
      return appUser;
    }
  }

  @Component
  private static class WebOAuth2AuthenticationSuccessHandler extends
      SimpleUrlAuthenticationSuccessHandler {

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
      this.setDefaultTargetUrl("/");
      super.onAuthenticationSuccess(request, response, authentication);
    }
  }
}
