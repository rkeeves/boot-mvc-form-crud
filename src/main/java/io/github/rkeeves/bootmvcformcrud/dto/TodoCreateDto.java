package io.github.rkeeves.bootmvcformcrud.dto;

import io.github.rkeeves.bootmvcformcrud.entity.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoCreateDto {

    @NotBlank(message = "The name cannot be empty")
    @Size(min = 4, max = 64, message = "The name must be at least 4 and at most 64 long")
    private String name;

    @NotNull(message = "Priority is required")
    private Priority priority;
}
