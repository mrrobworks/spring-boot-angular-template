package de.mrrobworks.springbootangular.backend.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class TemplateUser {

  @Id
  private String id;
  private String username;

  @OneToMany
  private final List<TemplateRole> roles = new ArrayList<>();
}
