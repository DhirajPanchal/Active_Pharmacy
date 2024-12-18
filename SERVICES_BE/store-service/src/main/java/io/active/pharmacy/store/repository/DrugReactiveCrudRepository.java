package io.active.pharmacy.store.repository;

import io.active.pharmacy.store.entity.Drug;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DrugReactiveCrudRepository extends ReactiveCrudRepository<Drug, Long> {

    // TEST
    Flux<Drug> findAllBy(Pageable pageable);

    // TEST
    Flux<Drug> findAllByAndActive(boolean active, Pageable pageable);

    // TEST
    Mono<Long> countByActive(boolean active);

}
