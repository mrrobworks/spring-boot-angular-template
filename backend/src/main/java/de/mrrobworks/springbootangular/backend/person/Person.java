package de.mrrobworks.springbootangular.backend.person;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "person")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Person {

  @Id private Long id;

  @NotNull
  @Size(min = 5, message = "Firstname must be at least 5 characters long")
  private String firstname;

  @NotNull private String lastname;
}
