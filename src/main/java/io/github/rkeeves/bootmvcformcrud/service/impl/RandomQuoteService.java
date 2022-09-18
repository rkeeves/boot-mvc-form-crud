package io.github.rkeeves.bootmvcformcrud.service.impl;

import io.github.rkeeves.bootmvcformcrud.service.QuoteService;

import java.util.List;
import java.util.Random;

public class RandomQuoteService implements QuoteService {

    private static final String FALLBACK_QUOTE = "It should've been cat pictures, I know...";

    private final Random random = new Random();

    private final List<String> quotes = List.of(
            FALLBACK_QUOTE,
            "This is a bad footnote",
            "But we always try again",
            "Where is the real footer?",
            "Still here?",
            "Don't google 'penguin mouth' images",
            "Out of luck. Still more.",
            "What were the 'must have's for Earth?",
            "But I thought e2e tests love non-determinism...",
            "Wacky waving inflatable arm flailing tube man",
            "Lorem ipsum exactly",
            "With this CO2 meta-strategy...we can finally defeat Earth!",
            "Keep on re-rendering, baby",
            "Yeah, but partial re-rendering has its own downsides too"
    );

    @Override
    public String quote() {
        return quotes.isEmpty() ? FALLBACK_QUOTE : quotes.get(random.nextInt(quotes.size()));
    }
}
