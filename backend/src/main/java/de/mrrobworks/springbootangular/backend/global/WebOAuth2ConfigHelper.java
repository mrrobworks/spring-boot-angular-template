package de.mrrobworks.springbootangular.backend.global;

import de.mrrobworks.springbootangular.backend.approle.AppRole;
import de.mrrobworks.springbootangular.backend.appuser.AppUser;
import de.mrrobworks.springbootangular.backend.appuser.AppUserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Slf4j
@Getter
@Component
@RequiredArgsConstructor
public class WebOAuth2ConfigHelper {

  private final WebOAuth2AuthoritiesMapper webOAuth2AuthoritiesMapper;
  private final WebOAuth2UserService webOAuth2UserService;
  private final WebOAuth2AuthenticationSuccessHandler webOAuth2AuthSuccessHandler;

  public static String getUserId(Map<String, Object> attributes) {
    HttpServletRequest currentRequest =
        ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    String requestURI = currentRequest.getRequestURI();
    String userId = null;

    // TODO: currentRequest URL is "/" so no distinction between google and github possible. Another solution is necessary
    // if (requestURI.equals(WebOAuth2Config.GOOGLE_LOGIN_URL)) {
    userId = String.valueOf(attributes.get(IdTokenClaimNames.SUB));
    // } else if (requestURI.equals(WebOAuth2Config.GITHUB_LOGIN_URL)) {
    //  userId = String.valueOf(attributes.get("id"));
    // }

    if (userId == null) {
      throw new BadCredentialsException("User-Id could not be determined.");
    }

    log.info("Identified User-Id \"{}\"", userId);
    return userId;
  }

  @Component
  @RequiredArgsConstructor
  private static class WebOAuth2AuthoritiesMapper implements GrantedAuthoritiesMapper {

    private final AppUserService appUserService;

    @Override
    public Collection<? extends GrantedAuthority> mapAuthorities(
        Collection<? extends GrantedAuthority> authorities) {
      log.info("Extract Authorities");

      if (authorities.isEmpty()) {
        return Collections.emptyList();
      }

      var oAuth2UserAuthority = (OAuth2UserAuthority) authorities.iterator().next();
      String userId = getUserId(oAuth2UserAuthority.getAttributes());

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
  private static class WebOAuth2UserService
      implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final AppUserService appUserService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest)
        throws OAuth2AuthenticationException {
      log.info("Extract Principal");

      Map<String, Object> attributes = oAuth2UserRequest.getAdditionalParameters();
      String userId = getUserId(attributes);
      AppUser appUser =
          appUserService.getAppUser(userId).orElseGet(() -> appUserService.createAppUser(userId));
      List<GrantedAuthority> authorityList =
          AuthorityUtils.createAuthorityList(
              appUser.getRoles().stream().map(AppRole::getId).toArray(String[]::new));

      String nameKeyAttribute = "";
      if (oAuth2UserRequest.getClientRegistration().getClientName().equals("Google")) {
        nameKeyAttribute = "id";
      } else if (oAuth2UserRequest.getClientRegistration().getClientName().equals("GitHub")) {
        nameKeyAttribute = IdTokenClaimNames.SUB;
      }

      return new DefaultOAuth2User(authorityList, attributes, nameKeyAttribute);
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
