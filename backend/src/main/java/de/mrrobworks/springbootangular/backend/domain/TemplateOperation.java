package de.mrrobworks.springbootangular.backend.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import lombok.Data;

@Data
@Entity
public class TemplateOperation implements GrantedAuthority {

  @Id
  private String id;

  @Override
  public String getAuthority() {
    return id;
  }
}
