package de.mrrobworks.springbootangular.backend.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TemplateUser {
  
  @Id
  private Long id;
  private String username;
}
