package io.github.rkeeves.bootmvcformcrud.service.impl;

import io.github.rkeeves.bootmvcformcrud.entity.Account;
import io.github.rkeeves.bootmvcformcrud.entity.Role;
import io.github.rkeeves.bootmvcformcrud.repository.AccountRepository;
import io.github.rkeeves.bootmvcformcrud.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

// Injected via Bean factory method in SecurityConfig
@RequiredArgsConstructor
public class JpaAuthenticationService implements AuthenticationService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
                .map(JpaAuthenticationService::customUserDetailsOf)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User by username '%s' not found", username)
                ));
    }

    private static CustomPrincipal customUserDetailsOf(Account account) {
        return CustomPrincipal.builder()
                .accountId(account.getId())
                .username(account.getUsername())
                .password(account.getPassword())
                .authorities(account.getRoles()
                        .stream()
                        .map(Role::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Optional<CustomPrincipal> getPrincipalFrom(Authentication authentication) {
        if (authentication == null) return Optional.empty();
        final var principal = authentication.getPrincipal();
        if (principal instanceof CustomPrincipal) return Optional.of((CustomPrincipal) principal);
        return Optional.empty();
    }
}
