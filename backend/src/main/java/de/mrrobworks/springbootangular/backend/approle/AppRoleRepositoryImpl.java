package de.mrrobworks.springbootangular.backend.approle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import javax.persistence.EntityNotFoundException;

public class AppRoleRepositoryImpl implements AppRoleRepositoryCustom, BeanFactoryAware {

  private AppRoleRepository appRoleRepository;

  @Override
  public AppRole findByIdGet(String id) {
    return appRoleRepository
        .findById(id)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format("Role \"%s\" does not exist.", id)));
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    appRoleRepository = beanFactory.getBean(AppRoleRepository.class);
  }
}
