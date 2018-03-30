package de.mrrobworks.springbootangular.backend.configuration;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import de.mrrobworks.springbootangular.backend.domain.TemplateUser;
import de.mrrobworks.springbootangular.backend.service.TemplateUserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
final class GooglePrincipalExtractor implements PrincipalExtractor {

  @Autowired
  private TemplateUserService templateUserService;

  @Override
  public Object extractPrincipal(Map<String, Object> map) {
    final String googleUserId = (String) map.get("sub");
    log.info("################GooglePrincipalExtractor {}", googleUserId);

    // Check if user already registered
    TemplateUser templateUser = templateUserService.getByGoogleId(googleUserId);

    if (templateUser == null) {
      // If the user isn't registered, create a new one
      final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//      final OAuth2AuthenticationDetails details =
//          ((OAuth2AuthenticationDetails) authentication.getDetails());
      // // Token for interact with Google
      // String token = ((OAuth2AuthenticationDetails)authentication.getDetails()).getTokenValue();
      // templateUser = new TemplateUser();
      //
      // final Google googleApi = new GoogleTemplate(token);
      // final TaskOperations taskOperations = googleApi.taskOperations();
      // TODO:...
      // templateUser.setRoles(Sets.newHashSet(Role.ROLE_USER, Role.ROLE_USER_GOOGLE));
      // templateUserService.create(templateUser);
    }
    return templateUser;
  }
}
