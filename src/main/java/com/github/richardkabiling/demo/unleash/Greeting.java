package com.github.richardkabiling.demo.unleash;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Greeting {
    private final String salutation;
    private final String name;
    private final String punctuation;

    public Greeting(String salutation, String name, String punctuation) {
        this.salutation = salutation;
        this.name = name;
        this.punctuation = punctuation;
    }

    public Greeting(String salutation, String name) {
        this(salutation, name, null);
    }

    public String getSalutation() {
        return salutation;
    }

    public String getName() {
        return name;
    }

    public String getPunctuation() {
        return punctuation;
    }
}
