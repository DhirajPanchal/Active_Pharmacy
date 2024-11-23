package io.active.pharmacy.gateway.service;


import io.active.pharmacy.gateway.dto.UserDto;
import io.active.pharmacy.gateway.entity.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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


    @PostMapping("/register")
    public Mono<User> register(@RequestBody(required = true) @Valid UserDto userDto) {
        log.info("_register ");
        System.out.println(userDto);

        return this.service.register(userDto);

    }


}
