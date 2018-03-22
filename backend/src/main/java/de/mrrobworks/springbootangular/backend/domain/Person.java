package de.mrrobworks.springbootangular.backend.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

  @Id
  private Long id;
  private String firstname;
  private String lastname;
}
