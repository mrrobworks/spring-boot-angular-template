package de.mrrobworks.springbootangular.backend.global;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// TODO: https://spring.io/guides/tutorials/spring-boot-oauth2/
@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
public class WebOAuth2Config extends WebSecurityConfigurerAdapter {

  static final String GOOGLE_LOGIN_URL = "/login/google";
  static final String GITHUB_LOGIN_URL = "/login/github";

  private final WebOAuth2ConfigHelper webOAuth2ConfigHelper;

  /*@Bean
  public WebClient rest(
      ClientRegistrationRepository clients, OAuth2AuthorizedClientRepository authz) {
    ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
        new ServletOAuth2AuthorizedClientExchangeFilterFunction(clients, authz);
    return WebClient.builder().filter(oauth2).build();
  }

  @GetMapping("/user")
  @ResponseBody
  public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
    return Collections.singletonMap("name", principal.getAttribute("name"));
  }*/

  /*@Override
  public void configure(WebSecurity web) {
    web.ignoring()
        .antMatchers(
            "/",
            // "/backend/user/info",
            "/**.html",
            "/**.js",
            "/**.css",
            "/**.ico",
            "/**.otf",
            "/**.ttf",
            "/**.woff",
            "/**.woff2",
            "/v2/api-docs",
            "/swagger-resources/**",
            "/configuration/**",
            "/webjars/**");
  }*/

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.antMatcher("/**")
        .authorizeRequests(
            a ->
                a.antMatchers(
                        "/",
                        "/login",
                        "/**.html",
                        "/**.js",
                        "/**.css",
                        "/**.ico",
                        "/**.otf",
                        "/**.ttf",
                        "/**.woff",
                        "/**.woff2",
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "/configuration/**",
                        "/webjars/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated())

        // .exceptionHandling(
        //    e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
        // .csrf(c -> c.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
        .csrf()
        .disable()
        // .logout(l -> l.logoutSuccessUrl("/").permitAll())
        .oauth2Login(
            o ->
                o.loginPage("/")
                    .authorizationEndpoint(
                        authorizationEndpoint -> authorizationEndpoint.baseUri("/login"))
                    .redirectionEndpoint(r -> r.baseUri("/"))
                    // .successHandler(webOAuth2ConfigHelper.getWebOAuth2AuthSuccessHandler())
                    .userInfoEndpoint(
                        userInfoEndpoint ->
                            userInfoEndpoint
                                .userAuthoritiesMapper(
                                    webOAuth2ConfigHelper.getWebOAuth2AuthoritiesMapper())
                                .userService(webOAuth2ConfigHelper.getWebOAuth2UserService())));
  }
}
