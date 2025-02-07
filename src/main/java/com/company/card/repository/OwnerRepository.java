package com.company.card.repository;

import com.company.card.domain.Owner;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OwnerRepository extends ReactiveCrudRepository<Owner, Long> {

}
