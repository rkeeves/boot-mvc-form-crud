package io.github.rkeeves.bootmvcformcrud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Long id;

    private String username;

    private boolean admin;
}
