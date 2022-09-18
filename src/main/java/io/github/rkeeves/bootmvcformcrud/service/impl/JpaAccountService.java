package io.github.rkeeves.bootmvcformcrud.service.impl;

import io.github.rkeeves.bootmvcformcrud.dto.AccountDto;
import io.github.rkeeves.bootmvcformcrud.entity.Account;
import io.github.rkeeves.bootmvcformcrud.entity.RoleName;
import io.github.rkeeves.bootmvcformcrud.exc.TodoAppException;
import io.github.rkeeves.bootmvcformcrud.repository.AccountRepository;
import io.github.rkeeves.bootmvcformcrud.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JpaAccountService implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public List<AccountDto> all() {
        return accountRepository.findAll()
                .stream()
                .map(JpaAccountService::accountDtoFor)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteNonAdminNonSelfById(Long id, CustomPrincipal deleter) {
        final Account account = accountRepository.findById(id)
                .orElseThrow(() -> new TodoAppException(
                        "User was not found"
                ));
        boolean wantsToDeleteSelf = Objects.equals(account.getId(), deleter.getAccountId());
        if (wantsToDeleteSelf) {
            throw new TodoAppException("You are not allowed to delete yourself");
        }
        boolean wantsToDeleteAnAdmin = isAdmin(account);
        if (wantsToDeleteAnAdmin) {
            throw new TodoAppException("You are not allowed to delete an admin");
        }
        accountRepository.delete(account);
    }

    private static AccountDto accountDtoFor(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .username(account.getUsername())
                .admin(isAdmin(account))
                .build();
    }

    private static boolean isAdmin(Account account) {
        return account.getRoles()
                .stream()
                .anyMatch(role -> Objects.equals(role.getName(), RoleName.ADMIN.getDataBaseValue()));
    }
}
