package de.mrrobworks.springbootangular.backend.configuration;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.filter.CompositeFilter;

@Slf4j
@EnableOAuth2Client
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  static final String GOOGLE_LOGIN_URL = "/login";
  static final String GITHUB_LOGIN_URL = "/login/github";

  @NonNull
  private final OAuth2ClientContext oauth2ClientContext;

  @NonNull
  private final WebOAuth2ConfigHelper webOAuth2ConfigHelper;

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
    final CompositeFilter filter = new CompositeFilter();
    final List<Filter> filters = new ArrayList<>();
    filters.add(googleOAuth2ProcessingFilter());
    filters.add(githubOAuth2ProcessingFilter());
    filter.setFilters(filters);
    return filter;
  }

  @Bean
  Filter googleOAuth2ProcessingFilter() {
    return new WebOAuth2ProcessingFilter(GOOGLE_LOGIN_URL, oauth2ClientContext,
        webOAuth2ConfigHelper, google());
//    return WebOAuth2ProcessingFilter.builder()
//        .defaultFilterProcessesUrl("/login")
//        .client(new ClientResources())
//        .oauth2ClientContext(oauth2ClientContext)
//        .webOAuth2ConfigHelper(webOAuth2ConfigHelper)
//        .build();
  }

  @Bean
  Filter githubOAuth2ProcessingFilter() {
    return new WebOAuth2ProcessingFilter(GITHUB_LOGIN_URL, oauth2ClientContext,
        webOAuth2ConfigHelper, github());
//    return WebOAuth2ProcessingFilter.builder()
//        .defaultFilterProcessesUrl("/login/github")
//        .client(new ClientResources())
//        .oauth2ClientContext(oauth2ClientContext)
//        .webOAuth2ConfigHelper(webOAuth2ConfigHelper)
//        .build();
  }

  @Bean
  @ConfigurationProperties("google")
  ClientResources google() {
    return new ClientResources();
  }

  @Bean
  @ConfigurationProperties("github")
  ClientResources github() {
    return new ClientResources();
  }

  @Bean
  FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(
      OAuth2ClientContextFilter filter) {
    FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<>();
    registration.setFilter(filter);
    registration.setOrder(-100);
    return registration;
  }

  class ClientResources {

    @NestedConfigurationProperty
    private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

    @NestedConfigurationProperty
    private ResourceServerProperties resource = new ResourceServerProperties();

    public AuthorizationCodeResourceDetails getClient() {
      return client;
    }

    public ResourceServerProperties getResource() {
      return resource;
    }
  }
}
