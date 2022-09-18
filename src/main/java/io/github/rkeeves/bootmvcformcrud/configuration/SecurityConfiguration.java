package io.github.rkeeves.bootmvcformcrud.configuration;

import io.github.rkeeves.bootmvcformcrud.repository.AccountRepository;
import io.github.rkeeves.bootmvcformcrud.service.AuthenticationService;
import io.github.rkeeves.bootmvcformcrud.service.impl.JpaAuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
        return http
                .authorizeRequests(auth -> auth
                        .antMatchers("/webjars/**", "/css/**", "/js/**").permitAll()
                        .mvcMatchers("/signin", "/signup").permitAll()
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/signin")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/todo/list"))
                .logout(logout -> logout
                        .logoutSuccessUrl("/signin"))
                .userDetailsService(userDetailsService)
                .build();
    }

    @Bean
    public AuthenticationService authenticationService(AccountRepository accountRepository) {
        return new JpaAuthenticationService(accountRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
    }
}
