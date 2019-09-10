package de.mrrobworks.springbootangular.backend.global;

import de.mrrobworks.springbootangular.backend.approle.AppRole;
import de.mrrobworks.springbootangular.backend.appuser.AppUser;
import de.mrrobworks.springbootangular.backend.appuser.AppUserService;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebOAuth2ConfigHelper {

  private final @NonNull WebOAuth2AuthoritiesExtractor webOAuth2AuthoritiesExtractor;
  private final @NonNull WebOAuth2PrincipalExtractor webOAuth2PrincipalExtractor;
  private final @NonNull WebOAuth2AuthenticationSuccessHandler webOAuth2AuthSuccessHandler;

  // TODO: throw BadCredentialsException if login failed
  private static String getUserId(Map<String, Object> map) {
    final HttpServletRequest currentRequest =
        ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
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

    private final @NonNull AppUserService appUserService;

    @Override
    public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
      final String userId = getUserId(map);
      log.info("Extract authorities for {}", userId);
      Optional<AppUser> appUser = appUserService.getByUserId(userId);
      if (appUser.isEmpty()) {
        return Collections.emptyList();
      }
      List<GrantedAuthority> authorityList =
          AuthorityUtils.createAuthorityList(
              appUser.get().getRoles().stream().map(AppRole::getId).toArray(String[]::new));
      return authorityList;
    }
  }

  @Component
  @RequiredArgsConstructor(onConstructor = @__(@Autowired))
  private static class WebOAuth2PrincipalExtractor implements PrincipalExtractor {

    private final @NonNull AppUserService appUserService;

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
      final String userId = getUserId(map);
      log.info("Extract Principal for {}", userId);
      final Optional<AppUser> appUser = appUserService.getByUserId(userId);
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
      this.setDefaultTargetUrl("/");
      super.onAuthenticationSuccess(request, response, authentication);
    }
  }
}
