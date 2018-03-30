package de.mrrobworks.springbootangular.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.mrrobworks.springbootangular.backend.domain.TemplateUser;

public interface TemplateUserRepository extends JpaRepository<TemplateUser, Long> {

}
