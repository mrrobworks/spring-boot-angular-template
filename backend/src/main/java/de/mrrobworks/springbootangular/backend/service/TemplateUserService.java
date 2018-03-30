package de.mrrobworks.springbootangular.backend.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import de.mrrobworks.springbootangular.backend.domain.TemplateUser;

public interface TemplateUserService extends UserDetailsService {

  public TemplateUser getByGoogleId(String id);
}
