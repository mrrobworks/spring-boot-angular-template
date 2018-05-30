package de.mrrobworks.springbootangular.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.mrrobworks.springbootangular.backend.domain.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {
}
