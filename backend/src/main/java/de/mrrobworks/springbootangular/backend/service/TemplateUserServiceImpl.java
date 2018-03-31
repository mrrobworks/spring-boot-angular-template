package de.mrrobworks.springbootangular.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import de.mrrobworks.springbootangular.backend.domain.TemplateUser;
import de.mrrobworks.springbootangular.backend.repository.TemplateUserRepository;

@Service
public class TemplateUserServiceImpl implements TemplateUserService {

  @Autowired
  private TemplateUserRepository templateUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TemplateUser getByGoogleId(String id) {
    final TemplateUser templateUser = templateUserRepository.findById(id).get();
    return templateUser;
  }
}
