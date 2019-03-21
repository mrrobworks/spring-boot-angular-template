package de.mrrobworks.springbootangular.backend.configuration;

import de.mrrobworks.springbootangular.backend.configuration.WebSecurityConfig.ClientResources;
//import lombok.Builder;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

class WebOAuth2ProcessingFilter extends OAuth2ClientAuthenticationProcessingFilter {

  private final WebOAuth2ConfigHelper webOAuth2ConfigHelper;
  private final OAuth2ClientContext oauth2ClientContext;
  private final ClientResources client;

  //@Builder
  WebOAuth2ProcessingFilter(String defaultFilterProcessesUrl, OAuth2ClientContext oauth2ClientContext,
      WebOAuth2ConfigHelper webOAuth2ConfigHelper, ClientResources client
  ) {
    super(defaultFilterProcessesUrl);
    this.webOAuth2ConfigHelper = webOAuth2ConfigHelper;
    this.oauth2ClientContext = oauth2ClientContext;
    this.client = client;
    init();
  }

  private void init() {
    setAuthenticationSuccessHandler(
        webOAuth2ConfigHelper.getWebOAuth2AuthenticationSuccessHandler());
    setRestTemplate(new OAuth2RestTemplate(client.getClient(), oauth2ClientContext));
    setTokenServices(tokenService(client));
  }

  private ResourceServerTokenServices tokenService(ClientResources client) {
    final UserInfoTokenServices userInfoTokenServices = new UserInfoTokenServices(
        client.getResource().getUserInfoUri(), client.getClient().getClientId());
    userInfoTokenServices
        .setPrincipalExtractor(webOAuth2ConfigHelper.getWebOAuth2PrincipalExtractor());
    userInfoTokenServices
        .setAuthoritiesExtractor(webOAuth2ConfigHelper.getWebOAuth2AuthoritiesExtractor());
    return userInfoTokenServices;
  }
}
