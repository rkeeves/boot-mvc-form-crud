package io.github.rkeeves.bootmvcformcrud.service;

import io.github.rkeeves.bootmvcformcrud.dto.AccountDto;
import io.github.rkeeves.bootmvcformcrud.service.impl.CustomPrincipal;

import java.util.List;

public interface AccountService {

    List<AccountDto> all();

    void deleteNonAdminNonSelfById(Long id, CustomPrincipal deleter);
}
