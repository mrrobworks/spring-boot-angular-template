package de.mrrobworks.springbootangular.backend.approle;

import de.mrrobworks.springbootangular.backend.appuser.AppUser;
import de.mrrobworks.springbootangular.backend.appuser.AppUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppRoleMapper {

  void updateDtoToAppRole(AppRoleDto appRoleDto, @MappingTarget AppRole appRole);

  AppRoleDto fromAppRole(AppRole appRole);

  @Mapping(target = "roles", ignore = true)
  AppUserDto appUserToAppUserDto(AppUser appUser);

  List<AppRoleDto> appRoleListToAppRoleDtoList(List<AppRole> list);
}
