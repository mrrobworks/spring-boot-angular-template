package de.mrrobworks.springbootangular.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.mrrobworks.springbootangular.backend.domain.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
}
