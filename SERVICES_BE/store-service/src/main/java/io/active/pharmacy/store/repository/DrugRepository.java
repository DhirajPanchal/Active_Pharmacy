package io.active.pharmacy.store.repository;

import io.active.pharmacy.store.entity.Drug;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DrugRepository extends ReactiveCrudRepository<Drug, Long> {

    Flux<Drug> findAllBy(Pageable pageable);

}
