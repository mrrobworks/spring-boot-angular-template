package de.mrrobworks.springbootangular.backend.global;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
public class WebOAuth2Config extends WebSecurityConfigurerAdapter {

  static final String GOOGLE_LOGIN_URL = "/login/google";
  static final String GITHUB_LOGIN_URL = "/login/github";

  private final WebOAuth2ConfigHelper webOAuth2ConfigHelper;

  @Override
  public void configure(WebSecurity web) {
    web.ignoring()
        .antMatchers(
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
    http.authorizeRequests()
        .antMatchers("/backend/**")
        .authenticated()
        .antMatchers("/noauth")
        .permitAll()
        .and()
        .csrf()
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        .and()
        .oauth2Login(
            oauth2Login ->
                oauth2Login
                    .loginPage("/noauth")
                    .authorizationEndpoint(
                        authorizationEndpoint -> authorizationEndpoint.baseUri("/login"))
                    .redirectionEndpoint(redirectionEndpoint -> redirectionEndpoint.baseUri("/"))
                    .userInfoEndpoint(
                        userInfoEndpoint ->
                            userInfoEndpoint
                                .userAuthoritiesMapper(
                                    webOAuth2ConfigHelper.getWebOAuth2AuthoritiesMapper())
                                .userService(webOAuth2ConfigHelper.getWebOAuth2UserService())));
  }
}
