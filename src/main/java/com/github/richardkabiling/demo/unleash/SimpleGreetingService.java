package com.github.richardkabiling.demo.unleash;

public class SimpleGreetingService implements GreetingService {
    @Override
    public Greeting getGreeting(String userId) {
        return new Greeting("Excelsior", userId);
    }
}
