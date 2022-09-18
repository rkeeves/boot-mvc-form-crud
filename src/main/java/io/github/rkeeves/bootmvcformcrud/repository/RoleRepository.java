package io.github.rkeeves.bootmvcformcrud.repository;

import io.github.rkeeves.bootmvcformcrud.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
