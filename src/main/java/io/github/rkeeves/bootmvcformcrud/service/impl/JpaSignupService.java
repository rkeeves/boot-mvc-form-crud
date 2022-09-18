package io.github.rkeeves.bootmvcformcrud.service.impl;

import io.github.rkeeves.bootmvcformcrud.dto.SignupDto;
import io.github.rkeeves.bootmvcformcrud.entity.Account;
import io.github.rkeeves.bootmvcformcrud.entity.RoleName;
import io.github.rkeeves.bootmvcformcrud.exc.ResourceExistsException;
import io.github.rkeeves.bootmvcformcrud.exc.TodoAppException;
import io.github.rkeeves.bootmvcformcrud.repository.AccountRepository;
import io.github.rkeeves.bootmvcformcrud.repository.RoleRepository;
import io.github.rkeeves.bootmvcformcrud.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JpaSignupService implements SignupService {

    private final PasswordEncoder passwordEncoder;

    private final AccountRepository accountRepository;

    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public void signup(SignupDto signupDto) {
        final var role = roleRepository.findByName(RoleName.USER.getDataBaseValue())
                .orElseThrow(() -> new TodoAppException("User role not found in database"));
        if (accountRepository.existsByUsername(signupDto.getUsername())) {
            throw new ResourceExistsException("Username already exists");
        };
        final var account = new Account();
        account.setUsername(signupDto.getUsername());
        account.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        account.setRoles(Set.of(role));
        accountRepository.save(account);
    }
}
