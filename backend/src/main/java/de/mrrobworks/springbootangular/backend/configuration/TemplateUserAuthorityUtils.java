package de.mrrobworks.springbootangular.backend.configuration;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import de.mrrobworks.springbootangular.backend.domain.TemplateUser;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TemplateUserAuthorityUtils {
  protected static final List<GrantedAuthority> ADMIN_ROLES =
      AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
  protected static final List<GrantedAuthority> USER_ROLES =
      AuthorityUtils.createAuthorityList("ROLE_USER");

  public static Collection<? extends GrantedAuthority> createAuthorities(
      TemplateUser templateUser) {
    String username = ""; // TODO: templateUser.getEmail()
    if (username.startsWith("admin")) {
      return ADMIN_ROLES;
    }
    return USER_ROLES;
  }
}
