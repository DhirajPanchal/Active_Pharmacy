package io.active.pharmacy.gateway.repository;

import io.active.pharmacy.gateway.entity.Address;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends ReactiveCrudRepository<Address, Long> {
}