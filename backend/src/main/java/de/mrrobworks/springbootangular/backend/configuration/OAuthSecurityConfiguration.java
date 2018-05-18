package de.mrrobworks.springbootangular.backend.configuration;

import javax.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableOAuth2Client
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OAuthSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @NonNull
  private final OAuth2ClientContext oauth2ClientContext;

  @NonNull
  private final AuthorizationCodeResourceDetails authorizationCodeResourceDetails;

  @NonNull
  private final ResourceServerProperties resourceServerProperties;

  @NonNull
  private final GooglePrincipalExtractor googlePrincipalExtractor;

  @NonNull
  private final GoogleAuthoritiesExtractor googleAuthoritiesExtractor;

  @NonNull
  private final GoogleAuthenticationSuccessHandler googleAuthenticationSuccessHandler;

  @Override
  @Transactional
  public void configure(HttpSecurity http) throws Exception {

    log.info("Configuration Websecurity for Application.");
    // @formatter:off
    http
    .logout()
    .logoutUrl("/logout")
    .logoutSuccessUrl("/")
    .and()
    
    .authorizeRequests()
    .antMatchers("/", "/**.html", "/**.js", "/**.css")
    .permitAll()
    .anyRequest()
    .authenticated()
    .and()
    
    .addFilterBefore(oAuth2ClientAuthenticationProcessingFilter(), BasicAuthenticationFilter.class)
    .csrf()
    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    // @formatter:on
  }

  private OAuth2ClientAuthenticationProcessingFilter oAuth2ClientAuthenticationProcessingFilter() {
    final OAuth2ClientAuthenticationProcessingFilter ssoFilter =
        new OAuth2ClientAuthenticationProcessingFilter("/login");
    ssoFilter.setAuthenticationSuccessHandler(googleAuthenticationSuccessHandler);
    ssoFilter.setRestTemplate(
        new OAuth2RestTemplate(authorizationCodeResourceDetails, oauth2ClientContext));
    ssoFilter.setTokenServices(userInfoTokenServices());
    return ssoFilter;
  }

  private UserInfoTokenServices userInfoTokenServices() {
    final UserInfoTokenServices userInfoTokenServices = new UserInfoTokenServices(
        resourceServerProperties.getUserInfoUri(), resourceServerProperties.getClientId());
    userInfoTokenServices.setPrincipalExtractor(googlePrincipalExtractor);
    userInfoTokenServices.setAuthoritiesExtractor(googleAuthoritiesExtractor);
    return userInfoTokenServices;
  }
}
