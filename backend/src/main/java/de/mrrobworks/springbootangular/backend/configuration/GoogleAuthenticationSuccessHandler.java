package de.mrrobworks.springbootangular.backend.configuration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
final class GoogleAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    this.setDefaultTargetUrl("/");
    super.onAuthenticationSuccess(request, response, authentication);
  }
}
