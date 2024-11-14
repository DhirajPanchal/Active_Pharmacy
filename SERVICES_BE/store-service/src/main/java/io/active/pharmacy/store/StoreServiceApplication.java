package io.active.pharmacy.store;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import io.active.pharmacy.store.config.R2DBCConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(R2DBCConfigurationProperties.class)
public class StoreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreServiceApplication.class, args);
    }

}

