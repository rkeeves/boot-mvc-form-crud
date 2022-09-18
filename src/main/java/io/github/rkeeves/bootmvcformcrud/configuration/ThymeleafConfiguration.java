package io.github.rkeeves.bootmvcformcrud.configuration;

import io.github.rkeeves.bootmvcformcrud.service.impl.RandomQuoteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThymeleafConfiguration {

    @Bean("quoteService")
    public RandomQuoteService randomQuoteService() {
        return new RandomQuoteService();
    }
}
