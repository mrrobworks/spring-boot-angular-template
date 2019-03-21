package de.mrrobworks.springbootangular.backend.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity-Class for a Person.
 *
 * @author robert
 */
@Entity
@Table(name = "person") // End JPA
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") // End Lombok
// TODO: JSR-303 Validation Rules for other attributes
public class Person {

  @Id
  private Long id;

  @Size(min = 5, max = 25, message = "Firstname must be at least 5 and maximum 25 characters")
  private String firstname;
  private String lastname;
}
