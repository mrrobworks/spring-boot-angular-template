package de.mrrobworks.springbootangular.backend.configuration;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Configuration;
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
@Configuration
@EnableOAuth2Client
@EnableWebSecurity
public class OAuthSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private OAuth2ClientContext oauth2ClientContext;

  @Autowired
  private AuthorizationCodeResourceDetails authorizationCodeResourceDetails;

  @Autowired
  private ResourceServerProperties resourceServerProperties;

  @Autowired
  private GooglePrincipalExtractor googlePrincipalExtractor;

  @Autowired
  private GoogleAuthoritiesExtractor googleAuthoritiesExtractor;

  @Transactional
  @Override
  public void configure(HttpSecurity http) throws Exception {

    log.info("#######################configure HttpSecurity http");
    // @formatter:off
    http
      .logout()
//      .logoutUrl("/logout")
      .logoutSuccessUrl("/")
      .and()
      
      .authorizeRequests()
      .antMatchers("/index.html", "/", "/**.html", "/**.js")
      .permitAll()
      .anyRequest()
      .authenticated()
      .and()
      
      .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
      .csrf()
      .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    // @formatter:on
  }

  private OAuth2ClientAuthenticationProcessingFilter ssoFilter() {

    final OAuth2ClientAuthenticationProcessingFilter oAuth2Filter =
        new OAuth2ClientAuthenticationProcessingFilter("/login");

    oAuth2Filter.setAuthenticationSuccessHandler(new GoogleAuthenticationSuccessHandler());

    final OAuth2RestTemplate oAuth2RestTemplate =
        new OAuth2RestTemplate(authorizationCodeResourceDetails, oauth2ClientContext);
    oAuth2Filter.setRestTemplate(oAuth2RestTemplate);

    // Getting the token and user details from the OAuth Service.
    final UserInfoTokenServices tokenServices = new UserInfoTokenServices(
        resourceServerProperties.getUserInfoUri(), resourceServerProperties.getClientId());
    tokenServices.setRestTemplate(oAuth2RestTemplate);
    tokenServices.setPrincipalExtractor(googlePrincipalExtractor);
    tokenServices.setAuthoritiesExtractor(googleAuthoritiesExtractor);
    
    oAuth2Filter.setTokenServices(tokenServices);

    return oAuth2Filter;
  }
}

