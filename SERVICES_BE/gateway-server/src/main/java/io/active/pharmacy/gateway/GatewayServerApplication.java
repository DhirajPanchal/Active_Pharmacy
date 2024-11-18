package io.active.pharmacy.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class GatewayServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(GatewayServerApplication.class, args);

    }

}
