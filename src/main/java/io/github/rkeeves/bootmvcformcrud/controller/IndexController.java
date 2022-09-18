package io.github.rkeeves.bootmvcformcrud.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Secured({ "ROLE_USER" })
    @GetMapping("/")
    public String onShowIndex() {
        return "index";
    }
}
