package io.github.rkeeves.bootmvcformcrud.controller;

import io.github.rkeeves.bootmvcformcrud.dto.SignupDto;
import io.github.rkeeves.bootmvcformcrud.exc.ResourceExistsException;
import io.github.rkeeves.bootmvcformcrud.service.SignupService;
import io.github.rkeeves.bootmvcformcrud.validation.FieldErrorBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class SignupController {

    private final SignupService signupService;

    @GetMapping("/signup")
    String onShowSignup(Model model) {
        model.addAttribute("signupDto", new SignupDto());
        return "signup";
    }

    @PostMapping("/signup")
    String onSubmitSignup(@Valid SignupDto signupDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        boolean passwordsMatch = Objects.equals(signupDto.getPassword(), signupDto.getRepeatedPassword());
        if (!passwordsMatch) {
            bindingResult
                    .addError(fieldErrorForNonMatchingRepeatedPassword(signupDto.getRepeatedPassword()));
            return "signup";
        }
        try {
            signupService.signup(signupDto);
        } catch (ResourceExistsException exc) {
            bindingResult
                    .addError(fieldErrorForAlreadyExistingUsername(signupDto.getUsername()));
            return "signup";
        } catch (Exception exc) {
            bindingResult.addError(new ObjectError("globalError", "Encountered error"));
            return "signup";
        }
        return "redirect:/signin";
    }

    private static FieldError fieldErrorForNonMatchingRepeatedPassword(String repeatedPassword) {
        return FieldErrorBuilder.builder()
                .objectName("signupDto")
                .fieldName("repeatedPassword")
                .rejectedValue(repeatedPassword)
                .message("Passwords didn't match")
                .build()
                .toFieldError();
    }

    private static FieldError fieldErrorForAlreadyExistingUsername(String username) {
        return FieldErrorBuilder.builder()
                .objectName("signupDto")
                .fieldName("username")
                .rejectedValue(username)
                .message("Username already exists")
                .build()
                .toFieldError();
    }
}
