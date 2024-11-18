package io.active.pharmacy.gateway.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TestService {

    @PreAuthorize("hasRole('ADMIN')")
    public Mono<String> greet() {
        return Mono.just("P3 Hello from service!");
    }

}