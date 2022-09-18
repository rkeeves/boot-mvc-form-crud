package io.github.rkeeves.bootmvcformcrud.repository;

import io.github.rkeeves.bootmvcformcrud.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByUsername(String username);

    Optional<Account> findByUsername(String username);
}
