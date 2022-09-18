package io.github.rkeeves.bootmvcformcrud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 4, max = 20, message = "Username must be at least 4 and at most 20 characters long")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 4, max = 20, message = "Password must be at least 4 and at most 20 characters long")
    private String password;

    private String repeatedPassword;
}
