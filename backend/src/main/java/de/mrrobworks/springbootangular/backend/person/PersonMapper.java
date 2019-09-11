package de.mrrobworks.springbootangular.backend.person;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

  List<PersonDto> personListToPersonDtoList(List<Person> list);
}
