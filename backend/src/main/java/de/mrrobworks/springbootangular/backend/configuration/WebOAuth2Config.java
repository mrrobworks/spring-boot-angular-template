package de.mrrobworks.springbootangular.backend.configuration;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.filter.CompositeFilter;

@Slf4j
@EnableOAuth2Client
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebOAuth2Config extends WebSecurityConfigurerAdapter {

  static final String GOOGLE_LOGIN_URL = "/login";
  static final String GITHUB_LOGIN_URL = "/login/github";

  private final @NonNull WebOAuth2ConfigHelper webOAuth2ConfigHelper;
  private final @NonNull OAuth2ClientContext oauth2ClientContext;

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers(
        "/",
        "/**.html",
        "/**.js",
        "/**.css",
        "/v2/api-docs",
        "/swagger-resources/**",
        "/configuration/**",
        "/webjars/**");
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/backend/**").authenticated()

        .and()
        .csrf()
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

        .and()
        .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
  }

  private Filter ssoFilter() {
    final CompositeFilter compositeFilter = new CompositeFilter();
    final List<Filter> filters = new ArrayList<>();
    filters.add(googleOAuth2AuthProcessingFilter());
    filters.add(githubOAuth2AuthProcessingFilter());
    compositeFilter.setFilters(filters);
    return compositeFilter;
  }

  private Filter googleOAuth2AuthProcessingFilter() {
    final WebOAuth2AuthProcessingFilter webOAuth2AuthProcessingFilter = new WebOAuth2AuthProcessingFilter(
        GOOGLE_LOGIN_URL, googleClientResource());
    webOAuth2AuthProcessingFilter.init();
    return webOAuth2AuthProcessingFilter;
  }

  private Filter githubOAuth2AuthProcessingFilter() {
    final WebOAuth2AuthProcessingFilter webOAuth2AuthProcessingFilter = new WebOAuth2AuthProcessingFilter(
        GITHUB_LOGIN_URL, githubClientResource());
    webOAuth2AuthProcessingFilter.init();
    return webOAuth2AuthProcessingFilter;
  }

  @Bean
  @ConfigurationProperties("google")
  ClientResources googleClientResource() {
    return new ClientResources();
  }

  @Bean
  @ConfigurationProperties("github")
  ClientResources githubClientResource() {
    return new ClientResources();
  }

  @Bean
  FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(
      OAuth2ClientContextFilter filter) {
    final FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<>();
    registration.setFilter(filter);
    registration.setOrder(-100);
    return registration;
  }

  private class WebOAuth2AuthProcessingFilter extends OAuth2ClientAuthenticationProcessingFilter {

    private final @NonNull ClientResources clientResources;

    WebOAuth2AuthProcessingFilter(String defaultFilterProcessesUrl,
        ClientResources clientResources) {
      super(defaultFilterProcessesUrl);
      this.clientResources = clientResources;
    }

    void init() {
      setAuthenticationSuccessHandler(webOAuth2ConfigHelper.getWebOAuth2AuthSuccessHandler());
      setRestTemplate(new OAuth2RestTemplate(clientResources.getClient(), oauth2ClientContext));
      setTokenServices(tokenServices());
    }

    private ResourceServerTokenServices tokenServices() {
      final UserInfoTokenServices tokenServices = new UserInfoTokenServices(
          clientResources.getResource().getUserInfoUri(),
          clientResources.getClient().getClientId());
      tokenServices.setPrincipalExtractor(webOAuth2ConfigHelper.getWebOAuth2PrincipalExtractor());
      tokenServices
          .setAuthoritiesExtractor(webOAuth2ConfigHelper.getWebOAuth2AuthoritiesExtractor());
      return tokenServices;
    }
  }

  @Getter
  private final class ClientResources {

    @NestedConfigurationProperty
    private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

    @NestedConfigurationProperty
    private ResourceServerProperties resource = new ResourceServerProperties();
  }
}
