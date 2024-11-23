package io.active.pharmacy.gateway.controller;


import io.active.pharmacy.gateway.dto.UserDto;
import io.active.pharmacy.gateway.entity.User;
import io.active.pharmacy.gateway.service.AuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

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


}
