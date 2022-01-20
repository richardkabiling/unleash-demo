package com.github.richardkabiling.demo.unleash;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

    private final DefaultGreetingService defaultGreetingService;
    private final UnleashProxyGreetingService unleashProxyGreetingService;
    private final UnleashVariantProxyGreetingService unleashVariantGreetingService;

    public GreetingController(DefaultGreetingService defaultGreetingService, UnleashProxyGreetingService unleashProxyGreetingService, UnleashVariantProxyGreetingService unleashVariantGreetingService) {
        this.defaultGreetingService = defaultGreetingService;
        this.unleashProxyGreetingService = unleashProxyGreetingService;
        this.unleashVariantGreetingService = unleashVariantGreetingService;
    }

    @GetMapping("/greeting/simple")
    @ResponseBody
    public Greeting getSalutationsViaDefault(@RequestHeader("x-user") String userId) {
        return defaultGreetingService.getGreeting(userId);
    }

    @GetMapping("/greeting/proxy")
    @ResponseBody
    public Greeting getSalutationsViaProxy(@RequestHeader("x-user") String userId) {
        return unleashProxyGreetingService.getGreeting(userId);
    }

    @GetMapping("/greeting/variant")
    @ResponseBody
    public Greeting getSalutationViaProxy(@RequestHeader("x-user") String userId) {
        return unleashVariantGreetingService.getGreeting(userId);
    }

}
