package de.mrrobworks.springbootangular.backend.approle;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository
    extends JpaRepository<AppRole, String>, AppRoleRepositoryCustom {}
