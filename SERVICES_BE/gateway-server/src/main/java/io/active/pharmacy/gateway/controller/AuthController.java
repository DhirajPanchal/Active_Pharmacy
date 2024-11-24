package io.active.pharmacy.gateway.controller;



import io.active.pharmacy.gateway.dto.UserDto;
import io.active.pharmacy.gateway.entity.User;
import io.active.pharmacy.gateway.security.TokenProvider;
import io.active.pharmacy.gateway.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final ReactiveUserDetailsService userDetailsService;
    private final TokenProvider tokenProvider;

    private final AuthService service;

    @GetMapping
    public String info() {
        return "Gateway Server - Auth";
    }


    @PostMapping("/registration")
    public ResponseEntity<Mono<User>> register(@RequestBody(required = true) @Valid UserDto userDto) {
        log.info("_register ");
        System.out.println(userDto);

        Mono<User> user = this.service.register(userDto);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    Mono<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return userDetailsService.findByUsername(loginRequest.username())
                .filter(u -> passwordEncoder.matches(loginRequest.password(), u.getPassword()))
                .map(tokenProvider::generateToken)
                .map(LoginResponse::new)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
    }

    @GetMapping("/profile")
    Mono<ProfileResponse> getProfile(Authentication authentication) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return Mono.just(new ProfileResponse(user.getUsername(), user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(name -> name.substring("ROLE_".length()))
                .collect(Collectors.toSet())
        ));
    }


    public record LoginRequest(String username, String password) {
    }

    record LoginResponse(String token) {
    }

    record ProfileResponse(String username, Set<String> roles) {
    }

}
