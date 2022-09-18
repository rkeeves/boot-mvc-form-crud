package io.github.rkeeves.bootmvcformcrud.controller;

import io.github.rkeeves.bootmvcformcrud.dto.TodoCreateDto;
import io.github.rkeeves.bootmvcformcrud.service.AuthenticationService;
import io.github.rkeeves.bootmvcformcrud.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class TodoCreateController {

    private final AuthenticationService authenticationService;

    private final TodoService todoService;

    @Secured({ "ROLE_USER" })
    @GetMapping(value = {"/todo/create"})
    public String onShowTodoCreate(Model model) {
        model.addAttribute("todoCreateDto", new TodoCreateDto());
        return "todo/create";
    }

    @Secured({ "ROLE_USER" })
    @PostMapping("/todo/create")
    public String onSubmitTodoCreate(Authentication authentication,
                                     @Valid @ModelAttribute("todoCreateDto") TodoCreateDto newDto,
                                     BindingResult result) {
        if (result.hasErrors()) {
            return "todo/create";
        }
        final var principal = authenticationService.getPrincipalFrom(authentication);
        if (principal.isEmpty()) {
            return "redirect:/";
        }
        todoService.create(newDto, principal.get());
        return "redirect:/todo/list";
    }
}
