package com.github.richardkabiling.demo.unleash;

public class PunctuatedGreetingService implements GreetingService {

    private final String punctuation;

    public PunctuatedGreetingService(String punctuation) {
        this.punctuation = punctuation;
    }

    @Override
    public Greeting getGreeting(String userId) {
        return new Greeting("Excelsior", userId, punctuation);
    }
}
