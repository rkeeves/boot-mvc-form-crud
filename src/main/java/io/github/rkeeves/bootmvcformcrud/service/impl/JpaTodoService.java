package io.github.rkeeves.bootmvcformcrud.service.impl;

import io.github.rkeeves.bootmvcformcrud.dto.TodoCreateDto;
import io.github.rkeeves.bootmvcformcrud.dto.TodoDto;
import io.github.rkeeves.bootmvcformcrud.entity.Account;
import io.github.rkeeves.bootmvcformcrud.entity.Todo;
import io.github.rkeeves.bootmvcformcrud.repository.AccountRepository;
import io.github.rkeeves.bootmvcformcrud.repository.TodoRepository;
import io.github.rkeeves.bootmvcformcrud.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JpaTodoService implements TodoService {

    private final AccountRepository accountRepository;

    private final TodoRepository todoRepository;

    @Override
    @Transactional
    public void markCompletedByIdAndAuthor(Long todoId, CustomPrincipal author) {
        todoRepository.markAsCompleteByIdAndAuthor(todoId, accountRefFor(author));
    }

    @Override
    @Transactional
    public void deleteByIdAndAuthor(Long todoId, CustomPrincipal author) {
        todoRepository.deleteByIdAndAuthor(todoId, accountRefFor(author));
    }

    @Override
    @Transactional
    public void create(TodoCreateDto todoCreateDto, CustomPrincipal author) {
        final var todo = Todo.builder()
                .name(todoCreateDto.getName())
                .priority(todoCreateDto.getPriority())
                .completed(false)
                .author(accountRefFor(author))
                .build();
        todoRepository.save(todo);
    }

    @Override
    public List<TodoDto> allByAuthor(CustomPrincipal author) {
        return todoRepository.findAllByAuthor(accountRefFor(author))
                .stream()
                .map(TodoDto::ofTodo)
                .collect(Collectors.toList());
    }

    private Account accountRefFor(CustomPrincipal customPrincipal) {
        return accountRepository.getReferenceById(customPrincipal.getAccountId());
    }
}
