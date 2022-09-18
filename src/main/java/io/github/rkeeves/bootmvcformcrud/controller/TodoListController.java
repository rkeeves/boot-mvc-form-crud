package io.github.rkeeves.bootmvcformcrud.controller;

import io.github.rkeeves.bootmvcformcrud.service.AuthenticationService;
import io.github.rkeeves.bootmvcformcrud.service.TodoService;
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
public class TodoListController {

    private final AuthenticationService authenticationService;

    private final TodoService todoService;

    @Secured({ "ROLE_USER" })
    @GetMapping("/todo/list")
    public String onShowTodoList(Authentication authentication, Model model) {
        final var principal = authenticationService.getPrincipalFrom(authentication);
        if (principal.isPresent()) {
            model.addAttribute("todos", todoService.allByAuthor(principal.get()));
        }
        return "todo/list";
    }

    @Secured({ "ROLE_USER" })
    @PostMapping("/todo/complete/{todoId}")
    public String onCompleteTodo(Authentication authentication,
                                 @PathVariable("todoId") Long todoId) {
        final var principal = authenticationService.getPrincipalFrom(authentication);
        if (principal.isPresent()) {
            todoService.markCompletedByIdAndAuthor(todoId, principal.get());
        }
        return "redirect:/todo/list";
    }

    @Secured({ "ROLE_USER" })
    @PostMapping("/todo/delete/{todoId}")
    public String onDeleteTodo(Authentication authentication,
                               @PathVariable("todoId") Long todoId) {
        final var principal = authenticationService.getPrincipalFrom(authentication);
        if (principal.isPresent()) {
            todoService.deleteByIdAndAuthor(todoId, principal.get());
        }
        return "redirect:/todo/list";
    }
}
