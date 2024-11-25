package io.active.pharmacy.gateway.service;

import io.active.pharmacy.gateway.dto.*;
import io.active.pharmacy.gateway.entity.User;
import io.active.pharmacy.gateway.exception.CustomErrorException;
import io.active.pharmacy.gateway.repository.UserRepository;
import io.active.pharmacy.gateway.security.TokenProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.active.pharmacy.gateway.Util.MappingUtil.dto2User;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final ReactiveUserDetailsService userDetailsService;
    private final TokenProvider tokenProvider;


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


    public Mono<LoginResponse> login(LoginRequest loginRequest) {
        return userDetailsService.findByUsername(loginRequest.getUsername())
                .filter(u -> passwordEncoder.matches(loginRequest.getPassword(), u.getPassword()))
                .map(tokenProvider::generateToken)
                .map(LoginResponse::new)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));

    }

    public Mono<ProfileResponse> profile(Authentication authentication) {


        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        Mono<User> userProfile = repository.findByEmail(user.getUsername());

        Mono<String> userName = Mono.just(user.getUsername());

        Mono<Set<String>> roles = Mono.just(user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(name -> name.substring("ROLE_".length()))
                .collect(Collectors.toSet()));


        return userProfile.zipWith(roles)
                .map(p -> new ProfileResponse(p.getT1(), p.getT2()));

//
//        Mono.just(
//                new ProfileResponse(
//                        user.getUsername(),
//                        user.getAuthorities().stream()
//                                .map(GrantedAuthority::getAuthority)
//                                .map(name -> name.substring("ROLE_".length()))
//                                .collect(Collectors.toSet())));

    }
}
