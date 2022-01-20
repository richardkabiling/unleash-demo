package com.github.richardkabiling.demo.unleash;

import io.getunleash.Unleash;
import io.getunleash.UnleashContext;

public class UnleashProxyGreetingService implements GreetingService {

    private final Unleash unleash;
    private final SimpleGreetingService simpleGreetingService;
    private final PunctuatedGreetingService punctuatedGreetingService;

    public UnleashProxyGreetingService(Unleash unleash,
                                       SimpleGreetingService simpleGreetingService,
                                       PunctuatedGreetingService punctuatedGreetingService) {
        this.unleash = unleash;
        this.simpleGreetingService = simpleGreetingService;
        this.punctuatedGreetingService = punctuatedGreetingService;
    }

    @Override
    public Greeting getGreeting(String userId) {
        var ctx = UnleashContext.builder()
                .userId(userId)
                .build();
        if (unleash.isEnabled("greeting.enable-punctuations", ctx)) {
            return punctuatedGreetingService.getGreeting(userId);
        } else {
            return simpleGreetingService.getGreeting(userId);
        }
    }
}
