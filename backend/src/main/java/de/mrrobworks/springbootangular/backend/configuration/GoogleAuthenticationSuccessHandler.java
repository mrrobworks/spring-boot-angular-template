package de.mrrobworks.springbootangular.backend.configuration;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
final class GoogleAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    final OAuth2Authentication o2auth = (OAuth2Authentication) authentication;
    log.info("Principal: {}", o2auth.getPrincipal());
    final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) o2auth.getDetails();
    log.info("Details: {}", details);

    log.info("Token value: {}", details.getTokenValue());

    log.info("Credentials: {}", o2auth.getCredentials());
    log.info("Name: {}", o2auth.getName());
    log.info("UserAuthentication: {}", o2auth.getUserAuthentication());

    final Map<String, String> details2 =
        (Map<String, String>) o2auth.getUserAuthentication().getDetails();
    final String principalId = details2.get("sub");
    log.info("Google-Account-Id (Sub, Principal-Id): {}", principalId);

    final Collection<GrantedAuthority> authorities = o2auth.getAuthorities();
    for (GrantedAuthority grantedAuthority : authorities) {
      log.info("Authority: {}", grantedAuthority.getAuthority());
    }

    // o2auth.getAuthorities().add(new SimpleGrantedAuthority("TEST_ROLE"));

    // o2auth.getAuthorities().addAll(new
    // ArrayList<>(AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER")));

    log.info("#################succes login: load roles");
    this.setDefaultTargetUrl("/");
    super.onAuthenticationSuccess(request, response, authentication);
  }
}
