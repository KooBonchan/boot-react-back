package com.company.card.repository;

import com.company.card.domain.Car;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CarRepository extends ReactiveCrudRepository<Car, Integer> {
  Mono<Car> findByName(String name);
}
