package de.mrrobworks.springbootangular.backend.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Person {
  
  @Id
  private Long id;
  private String firstname;
  private String lastname;
}
