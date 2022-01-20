package com.github.richardkabiling.demo.unleash;

import io.getunleash.Unleash;
import io.getunleash.UnleashContext;
import io.getunleash.Variant;
import io.getunleash.variant.Payload;

public class UnleashVariantProxyGreetingService implements GreetingService {

    private final Unleash unleash;
    private final SimpleGreetingService simpleGreetingService;
    private final PunctuatedGreetingServiceFactory punctuatedGreetingServiceFactory;

    public UnleashVariantProxyGreetingService(Unleash unleash,
                                              SimpleGreetingService simpleGreetingService,
                                              PunctuatedGreetingServiceFactory punctuatedGreetingServiceFactory) {
        this.unleash = unleash;
        this.simpleGreetingService = simpleGreetingService;
        this.punctuatedGreetingServiceFactory = punctuatedGreetingServiceFactory;
    }

    @Override
    public Greeting getGreeting(String userId) {
        var ctx = UnleashContext.builder()
                .userId(userId)
                .build();
        Variant variant = unleash.getVariant("greeting.enable-punctuations", ctx);
        if (variant.isEnabled()) {
            var punctuation = variant.getPayload()
                    .map(Payload::getValue)
                    .orElse(null);
            return punctuatedGreetingServiceFactory.create(punctuation)
                    .getGreeting(userId);
        } else {
            return simpleGreetingService.getGreeting(userId);
        }
    }
}
