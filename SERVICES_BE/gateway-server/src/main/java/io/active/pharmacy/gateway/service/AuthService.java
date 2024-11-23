package io.active.pharmacy.gateway.service;

import io.active.pharmacy.gateway.dto.UserDto;
import io.active.pharmacy.gateway.entity.User;
import io.active.pharmacy.gateway.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static io.active.pharmacy.gateway.Util.MappingUtil.dto2User;

@Slf4j
@Service
public class AuthService {

    private final UserRepository repository;

    public AuthService(UserRepository repository) {
        this.repository = repository;
    }

    public Mono<User> register(UserDto userDto) {
        log.info("_register srv");

        System.out.println("START");

        return  repository.findAllByEmail(userDto.getEmail())
                .flatMap(__ -> Mono.error(new RuntimeException("User already exists with email [ " + userDto.getEmail() + " ]")))
                .switchIfEmpty(Mono.defer(() -> repository.save(dto2User(userDto)))).cast(User.class);


    }


}
