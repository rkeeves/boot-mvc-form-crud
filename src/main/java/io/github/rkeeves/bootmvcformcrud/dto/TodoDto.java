package io.github.rkeeves.bootmvcformcrud.dto;

import io.github.rkeeves.bootmvcformcrud.entity.Priority;
import io.github.rkeeves.bootmvcformcrud.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {

    private Long id;

    private String name;

    private Priority priority;

    private Boolean completed;

    public static TodoDto ofTodo(Todo todo) {
        return TodoDto.builder()
                .id(todo.getId())
                .name(todo.getName())
                .completed(todo.getCompleted())
                .priority(todo.getPriority())
                .build();
    }
}
