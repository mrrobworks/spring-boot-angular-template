package de.mrrobworks.springbootangular.backend.appuser;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import javax.persistence.EntityNotFoundException;

public class AppUserRepositoryImpl implements AppUserRepositoryCustom, BeanFactoryAware {

  private AppUserRepository appUserRepository;

  @Override
  public AppUser findByIdGet(String id) {
    return appUserRepository
        .findById(id)
        .orElseThrow(
            () ->
                new EntityNotFoundException(
                    String.format("User with id \"%s\" could not be found.", id)));
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    appUserRepository = beanFactory.getBean(AppUserRepository.class);
  }
}
