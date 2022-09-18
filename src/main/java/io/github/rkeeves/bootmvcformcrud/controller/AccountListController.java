package io.github.rkeeves.bootmvcformcrud.controller;

import io.github.rkeeves.bootmvcformcrud.dto.AccountListDto;
import io.github.rkeeves.bootmvcformcrud.exc.TodoAppException;
import io.github.rkeeves.bootmvcformcrud.service.AccountService;
import io.github.rkeeves.bootmvcformcrud.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AccountListController {

    private final AuthenticationService authenticationService;

    private final AccountService accountService;

    @Secured({ "ROLE_ADMIN" })
    @GetMapping("/account/list")
    public String onShowAccountList(Authentication authentication, Model model) {
        final var principal = authenticationService.getPrincipalFrom(authentication);
        if (principal.isPresent()) {
            model.addAttribute("accountListDto", new AccountListDto(accountService.all()));
        }
        return "account/list";
    }

    @Secured({ "ROLE_ADMIN" })
    @PostMapping("/account/delete/{accountId}")
    public String onDeleteAccount(Authentication authentication,
                                  @PathVariable("accountId") Long accountId) {
        final var principal = authenticationService.getPrincipalFrom(authentication);
        try {
            if (principal.isPresent()) {
                accountService.deleteNonAdminNonSelfById(accountId, principal.get());
            }
            return "redirect:/account/list";
        } catch (TodoAppException exc) {
            return "redirect:/account/list?error";
        }
    }
}
