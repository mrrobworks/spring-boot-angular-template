package de.mrrobworks.springbootangular.backend.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.security.core.GrantedAuthority;
import lombok.Data;

@Data
@Entity
public class TemplateRole implements GrantedAuthority {

  @Id
  private String id;

  @OneToMany
  private final List<TemplateOperation> allowedOperations = new ArrayList<>();

  @Override
  public String getAuthority() {
    return id;
  }
}
