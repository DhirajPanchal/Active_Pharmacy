package io.active.pharmacy.store.init;

import io.active.pharmacy.store.entity.Drug;
import io.active.pharmacy.store.repository.DrugReactiveCrudRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@AllArgsConstructor
public class DataInitializer {


    private final DrugReactiveCrudRepository repo;

    @Bean
    public CommandLineRunner initializeData() {
        System.out.println("========================================================= CommandLineRunner I N I T ");
        return args -> {
//            List<Drug> list = new ArrayList<>();
//
//            list.add(new Drug("FreeStyle Libre", 8, "ANTIDIABETICS", 17, "Glucometer"));
//            list.add(new Drug("CONTOUR NEXT ONE", 8, "ANTIDIABETICS", 17, "Glucometer"));
//            list.add(new Drug("Accu-Chek Guide Blood Glucose Meter", 8, "ANTIDIABETICS", 17, "Glucometer"));
//            list.add(new Drug("True metrex", 8, "ANTIDIABETICS", 17, "Glucometer"));
//            list.add(new Drug("Accusure Simple Blood Glucometer", 8, "ANTIDIABETICS", 17, "Glucometer"));
//
//            repo.saveAll(list).subscribe();

        };
    }

}