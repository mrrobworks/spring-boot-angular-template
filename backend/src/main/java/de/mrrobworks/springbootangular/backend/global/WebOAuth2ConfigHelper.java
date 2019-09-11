package de.mrrobworks.springbootangular.backend.global;

import de.mrrobworks.springbootangular.backend.approle.AppRole;
import de.mrrobworks.springbootangular.backend.appuser.AppUser;
import de.mrrobworks.springbootangular.backend.appuser.AppUserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Getter
@Component
@RequiredArgsConstructor
public class WebOAuth2ConfigHelper {

  private final WebOAuth2AuthoritiesExtractor webOAuth2AuthoritiesExtractor;
  private final WebOAuth2PrincipalExtractor webOAuth2PrincipalExtractor;
  private final WebOAuth2AuthenticationSuccessHandler webOAuth2AuthSuccessHandler;

  private static String getUserId(Map<String, Object> map) {
    HttpServletRequest currentRequest =
        ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    String requestURI = currentRequest.getRequestURI();
    String userId = null;

    if (requestURI.equals(WebOAuth2Config.GOOGLE_LOGIN_URL)) {
      userId = String.valueOf(map.get("sub"));
    } else if (requestURI.equals(WebOAuth2Config.GITHUB_LOGIN_URL)) {
      userId = String.valueOf(map.get("id"));
    }

    if (userId == null) {
      throw new BadCredentialsException("User-Id could not be determined.");
    }

    log.info("Identified User-Id \"{}\"", userId);
    return userId;
  }

  @Component
  @RequiredArgsConstructor
  private static class WebOAuth2AuthoritiesExtractor implements AuthoritiesExtractor {

    private final AppUserService appUserService;

    @Override
    public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
      log.info("Extract Authorities");
      String userId = getUserId(map);

      Optional<AppUser> optionalAppUser = appUserService.getAppUser(userId);
      if (optionalAppUser.isEmpty()) {
        return Collections.emptyList();
      }

      return AuthorityUtils.createAuthorityList(
          optionalAppUser.get().getRoles().stream().map(AppRole::getId).toArray(String[]::new));
    }
  }

  @Component
  @RequiredArgsConstructor
  private static class WebOAuth2PrincipalExtractor implements PrincipalExtractor {

    private final AppUserService appUserService;

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
      log.info("Extract Principal");
      String userId = getUserId(map);
      Optional<AppUser> appUser = appUserService.getAppUser(userId);
      return appUser.orElseGet(() -> appUserService.createAppUser(userId));
    }
  }

  @Component
  private static class WebOAuth2AuthenticationSuccessHandler
      extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException {
      setDefaultTargetUrl("/");
      super.onAuthenticationSuccess(request, response, authentication);
    }
  }
}
