package io.github.rkeeves.bootmvcformcrud.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountListDto {

    private final List<AccountDto> accountDtos;
}
