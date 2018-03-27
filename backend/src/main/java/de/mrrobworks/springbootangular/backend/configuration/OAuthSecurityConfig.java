package de.mrrobworks.springbootangular.backend.configuration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableOAuth2Client
@EnableWebSecurity
public class OAuthSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private OAuth2ClientContext oauth2ClientContext;

  @Autowired
  private AuthorizationCodeResourceDetails authorizationCodeResourceDetails;

  @Autowired
  private ResourceServerProperties resourceServerProperties;

  @Override
  public void configure(HttpSecurity http) throws Exception {

    // @formatter:off
    http
      .logout().logoutSuccessUrl("/")
      .and()
      
      .authorizeRequests()
      .antMatchers("/index.html", "/", "/**.html", "/**.js").permitAll()
      .anyRequest().authenticated()
      .and()
          
      .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
      .csrf()
      .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    // @formatter:on
  }

  private OAuth2ClientAuthenticationProcessingFilter ssoFilter() {

    final OAuth2ClientAuthenticationProcessingFilter oAuth2Filter =
        new OAuth2ClientAuthenticationProcessingFilter("/login");

    oAuth2Filter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler() {
      public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
          Authentication authentication) throws IOException, ServletException {
        this.setDefaultTargetUrl("/");
        super.onAuthenticationSuccess(request, response, authentication);
      }
    });

    final OAuth2RestTemplate oAuth2RestTemplate =
        new OAuth2RestTemplate(authorizationCodeResourceDetails, oauth2ClientContext);
    oAuth2Filter.setRestTemplate(oAuth2RestTemplate);

    // Getting the token and user details from the OAuth Service.
    final UserInfoTokenServices tokenServices = new UserInfoTokenServices(
        resourceServerProperties.getUserInfoUri(), resourceServerProperties.getClientId());
    tokenServices.setRestTemplate(oAuth2RestTemplate);
    oAuth2Filter.setTokenServices(tokenServices);

    return oAuth2Filter;
  }
}

