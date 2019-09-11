package de.mrrobworks.springbootangular.backend.person;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class PersonDto {

  private Long id;

  @Size(min = 5, message = "Firstname must be at least 5 characters long")
  private String firstname;

  private String lastname;
}
