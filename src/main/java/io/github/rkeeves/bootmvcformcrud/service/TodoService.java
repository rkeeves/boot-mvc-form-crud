package io.github.rkeeves.bootmvcformcrud.service;

import io.github.rkeeves.bootmvcformcrud.dto.TodoCreateDto;
import io.github.rkeeves.bootmvcformcrud.dto.TodoDto;
import io.github.rkeeves.bootmvcformcrud.service.impl.CustomPrincipal;

import java.util.List;

public interface TodoService {

    void markCompletedByIdAndAuthor(Long todoId, CustomPrincipal author);

    void deleteByIdAndAuthor(Long todoId, CustomPrincipal author);

    void create(TodoCreateDto todoCreateDto, CustomPrincipal author);

    List<TodoDto> allByAuthor(CustomPrincipal author);
}
