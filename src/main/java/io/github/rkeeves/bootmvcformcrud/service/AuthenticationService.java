package io.github.rkeeves.bootmvcformcrud.service;

import io.github.rkeeves.bootmvcformcrud.service.impl.CustomPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface AuthenticationService extends UserDetailsService {

    Optional<CustomPrincipal> getPrincipalFrom(Authentication authentication);
}
