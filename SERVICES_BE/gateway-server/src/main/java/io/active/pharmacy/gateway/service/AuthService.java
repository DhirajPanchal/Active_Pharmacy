package io.active.pharmacy.gateway.service;

import io.active.pharmacy.gateway.dto.CustomErrorResponse;
import io.active.pharmacy.gateway.dto.UserDto;
import io.active.pharmacy.gateway.entity.User;
import io.active.pharmacy.gateway.exception.CustomErrorException;
import io.active.pharmacy.gateway.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.UUID;

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

        return repository.findByEmail(userDto.getEmail())
                .flatMap(__ -> {
                            CustomErrorResponse customErrorResponse = CustomErrorResponse
                                    .builder()
                                    .traceId(UUID.randomUUID().toString())
                                    .timestamp(OffsetDateTime.now().now())
                                    .status(HttpStatus.BAD_REQUEST)
                                    .statusCode(HttpStatus.BAD_REQUEST.value())
                                    .build();
                            throw new CustomErrorException("User already exists with email [ " + userDto.getEmail() + " ]", customErrorResponse);
                        }
                )
                .switchIfEmpty(Mono.defer(() -> repository.save(dto2User(userDto)))).cast(User.class);


    }


}
