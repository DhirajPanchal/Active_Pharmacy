package io.active.pharmacy.gateway.controller;


import io.active.pharmacy.gateway.dto.LoginRequest;
import io.active.pharmacy.gateway.dto.LoginResponse;
import io.active.pharmacy.gateway.dto.UserDto;
import io.active.pharmacy.gateway.entity.User;
import io.active.pharmacy.gateway.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {


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

        Mono<LoginResponse> response = this.service.login(loginRequest);

        return response;
    }

    @GetMapping("/profile")
    Mono<UserDto> getProfile(Authentication authentication) {

        Mono<UserDto> response = this.service.profile(authentication);

        return response;

    }


}
