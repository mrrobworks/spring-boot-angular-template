package de.mrrobworks.springbootangular.backend.configuration;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import de.mrrobworks.springbootangular.backend.domain.TemplateRole;
import de.mrrobworks.springbootangular.backend.domain.TemplateUser;
import de.mrrobworks.springbootangular.backend.service.TemplateUserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GoogleAuthoritiesExtractor implements AuthoritiesExtractor {

  @Autowired
  public TemplateUserService templateUserService;

  //@Transactional
  @Override
  public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {

    log.info("################GoogleAuthoritiesExtractor");

    final String googleUserId = (String) map.get("sub");
    final TemplateUser templateUser = templateUserService.getByGoogleId(googleUserId);

    if (templateUser == null) {
      return Collections.<GrantedAuthority>emptyList();
    }

    templateUser.getRoles().size();
    
    final List<TemplateRole> roles = templateUser.getRoles();
    final Stream<TemplateRole> stream = roles.stream();


    final String[] array = stream.map(t -> t.getAuthority()).toArray(size -> new String[size]);
    return AuthorityUtils.createAuthorityList(array);

    // return AuthorityUtils.createAuthorityList(Arrays.asList("ROLE_USER_TEST", "ROLE_ADMIN_TEST")
    // .stream().toArray(size -> new String[size]));
  }
}
