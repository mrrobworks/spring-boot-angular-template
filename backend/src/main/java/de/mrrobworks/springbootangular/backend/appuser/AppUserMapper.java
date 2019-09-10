package de.mrrobworks.springbootangular.backend.appuser;

import de.mrrobworks.springbootangular.backend.approle.AppRole;
import de.mrrobworks.springbootangular.backend.approle.AppRoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

  void updateDtoToAppUser(AppUserDto appUserDto, @MappingTarget AppUser appUser);

  AppUserDto fromAppUser(AppUser appUser);

  @Mapping(target = "users", ignore = true)
  AppRoleDto appRoleToAppRoleDto(AppRole appRole);

  List<AppUserDto> appUserListToAppUserDtoList(List<AppUser> list);
}
