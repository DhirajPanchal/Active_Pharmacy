package io.active.pharmacy.store.web;

import io.active.pharmacy.store.entity.Drug;
import io.active.pharmacy.store.repository.DrugReactiveCrudRepository;
import io.active.pharmacy.store.service.ListingReactiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("api/v1/drug")
public class DrugController {

    private final DrugReactiveCrudRepository repository;

    private final ListingReactiveService service;

    public DrugController(DrugReactiveCrudRepository repository, ListingReactiveService service) {
        this.repository = repository;
        this.service = service;
    }


    @GetMapping("/{drugId}")
    public ResponseEntity<Mono<Drug>> getDrugById(@PathVariable Long drugId) {

        log.info(" _ DRUG.GET : " + drugId);


        // Type conversion error here NOT in ListingReactiveController (DatabaseClient).
        // Using ListingReactiveController instead for now.
        // Mono<Drug> drug = this.repository.findById(drugId);

        Mono<Drug> drug = this.service.getDrugById(drugId);

        return new ResponseEntity<>(drug, HttpStatus.OK);

    }
}
