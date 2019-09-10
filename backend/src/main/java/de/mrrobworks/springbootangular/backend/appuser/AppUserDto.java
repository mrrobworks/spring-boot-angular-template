package de.mrrobworks.springbootangular.backend.appuser;

import de.mrrobworks.springbootangular.backend.approle.AppRoleDto;
import lombok.Data;

import java.util.List;

@Data
public class AppUserDto {

  private String id;
  private List<AppRoleDto> roles;
}
