package de.mrrobworks.springbootangular.backend.appuser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository
    extends JpaRepository<AppUser, String>, AppUserRepositoryCustom {}
