package de.mrrobworks.springbootangular.backend.approle;

import de.mrrobworks.springbootangular.backend.appuser.AppUserDto;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class AppRoleDto {

  private String id;

  @Size(min = 5, message = "Description must be at least 5 characters long")
  private String description;

  private List<AppUserDto> users;
}
