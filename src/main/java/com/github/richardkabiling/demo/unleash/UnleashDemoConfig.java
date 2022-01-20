package com.github.richardkabiling.demo.unleash;

import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.event.UnleashReady;
import io.getunleash.event.UnleashSubscriber;
import io.getunleash.repository.FeatureToggleResponse;
import io.getunleash.repository.ToggleCollection;
import io.getunleash.util.UnleashConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class UnleashDemoConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public Unleash unleash() {
        var config = new UnleashConfig.Builder()
                .appName("salutations-service")
                .instanceId(UUID.randomUUID().toString())
                .unleashAPI("http://unleash/api")
                .fetchTogglesInterval(10)
                .synchronousFetchOnInitialisation(true)
                .customHttpHeader("Authorization", "*:development.7817033d6208f9fc83187fc6675550bd4256340a9695cf22f1895f97")
                .subscriber(new UnleashSubscriber() {

                    @Override
                    public void onReady(UnleashReady unleashReady) {
                        logger.info("Unleash ready");
                    }

                    @Override
                    public void togglesFetched(FeatureToggleResponse toggleResponse) {
                        logger.info("Features fetched");
                    }

                    @Override
                    public void togglesBackedUp(ToggleCollection toggleCollection) {
                        logger.info("Features stored");
                    }
                })
                .build();

        return new DefaultUnleash(config);
    }

    @Bean
    public DefaultGreetingService defaultGreetingService(Unleash unleash) {
        return new DefaultGreetingService(unleash);
    }

    @Bean
    public SimpleGreetingService simpleGreetingService() {
        return new SimpleGreetingService();
    }

    @Bean
    public PunctuatedGreetingServiceFactory punctuatedGreetingServiceFactory() {
        return new PunctuatedGreetingServiceFactory();
    }

    @Bean
    public PunctuatedGreetingService punctuatedGreetingService(PunctuatedGreetingServiceFactory factory) {
        return factory.create("!");
    }

    @Bean
    public UnleashProxyGreetingService unleashProxyGreetingService(Unleash unleash,
                                                                   SimpleGreetingService simpleGreetingService,
                                                                   PunctuatedGreetingService punctuatedGreetingService) {
        return new UnleashProxyGreetingService(unleash, simpleGreetingService, punctuatedGreetingService);
    }

    @Bean
    public UnleashVariantProxyGreetingService unleashVariantProxyGreetingService(Unleash unleash,
                                                                                 SimpleGreetingService simpleGreetingService,
                                                                                 PunctuatedGreetingServiceFactory factory) {
        return new UnleashVariantProxyGreetingService(unleash, simpleGreetingService, factory);
    }
}
