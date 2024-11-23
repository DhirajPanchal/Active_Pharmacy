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

//
//    @Bean
//    public RouteLocator activeKartRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
//
//        return routeLocatorBuilder.routes()
//                .route(p -> p
//                        .path("/active-pharmacy/inventory/**")
//                        .filters(f -> f
//                                .rewritePath("/active-pharmacy/inventory/(?<segment>.*)", "/${segment}")
//                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
//                        .uri("lb://INVENTORY-SERVICE"))
//                .route(p -> p
//                        .path("/active-pharmacy/store/**")
//                        .filters(f -> f
//                                .rewritePath("/active-pharmacy/store/(?<segment>.*)", "/${segment}")
//                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
//                        .uri("lb://STORE-SERVICE"))
//                .build();
//
//    }
//


}
