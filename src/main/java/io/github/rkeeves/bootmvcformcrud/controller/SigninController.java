package io.github.rkeeves.bootmvcformcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SigninController {

    @GetMapping("/signin")
    public String onShowSignin() {
        return "signin";
    }
}
