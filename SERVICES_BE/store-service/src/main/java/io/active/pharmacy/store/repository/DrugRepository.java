package io.active.pharmacy.store.repository;

import io.active.pharmacy.store.entity.Drug;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DrugRepository extends ReactiveCrudRepository<Drug, Long> {

    Flux<Drug> findAllBy(Pageable pageable);

    Flux<Drug> findAllByAndActive(boolean active, Pageable pageable);

    Mono<Long> countByActive(boolean active);

}
