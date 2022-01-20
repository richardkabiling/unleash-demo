package com.github.richardkabiling.demo.unleash;

import io.getunleash.Unleash;
import io.getunleash.UnleashContext;

public class DefaultGreetingService implements GreetingService {

    private final Unleash unleash;

    public DefaultGreetingService(Unleash unleash) {
        this.unleash = unleash;
    }

    @Override
    public Greeting getGreeting(String userId) {
        var ctx = UnleashContext.builder()
                .userId(userId)
                .build();
        if (unleash.isEnabled("greeting.enable-punctuations", ctx)) {
            return new Greeting("Excelsior", userId, "!");
        } else {
            return new Greeting("Excelsior", userId);
        }
    }
}
