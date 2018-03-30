package de.mrrobworks.springbootangular.backend.configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import de.mrrobworks.springbootangular.backend.domain.TemplateUser;
import de.mrrobworks.springbootangular.backend.service.TemplateUserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
final class GoogleAuthoritiesExtractor implements AuthoritiesExtractor {

  @Autowired
  public TemplateUserService templateUserService;

  @Override
  public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {

    log.info("################GoogleAuthoritiesExtractor");

    final String googleUserId = (String) map.get("sub");
    final TemplateUser templateUser = templateUserService.getByGoogleId(googleUserId);

//    if (templateUser == null) {
//      return Collections.<GrantedAuthority>emptyList();
//    }

    // TODO:...
    // return AuthorityUtils
    // .createAuthorityList(templateUser.getRoles().stream().toArray(size -> new String[size]));
    return AuthorityUtils.createAuthorityList(Arrays.asList("ROLE_USER_TEST", "ROLE_ADMIN_TEST")
        .stream().toArray(size -> new String[size]));
  }
}
