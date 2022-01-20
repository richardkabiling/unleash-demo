package com.github.richardkabiling.demo.unleash;

public class PunctuatedGreetingServiceFactory {

    public PunctuatedGreetingService create(String punctuation) {
        return new PunctuatedGreetingService(punctuation);
    }

}
