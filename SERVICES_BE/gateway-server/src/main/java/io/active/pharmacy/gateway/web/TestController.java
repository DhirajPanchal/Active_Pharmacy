package io.active.pharmacy.gateway.web;


import io.active.pharmacy.gateway.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
public class TestController {

    private final TestService service;

    public TestController(TestService service) {
        this.service = service;
    }

    @GetMapping("/")
    public Mono<String> greet(Mono<Principal> principal) {
        return principal
                .map(Principal::getName)
                .map(name -> String.format("P1  Hello, %s", name));
    }

    @GetMapping("/admin")
    public Mono<String> greetAdmin(Mono<Principal> principal) {
        return principal
                .map(Principal::getName)
                .map(name -> String.format("P2 Admin access: %s", name));
    }

    @GetMapping("/service")
    public Mono<String> greetingService() {
        return service.greet();
    }

}
