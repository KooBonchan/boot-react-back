package com.company.card.repository;

import com.company.card.domain.Car;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CarRepository extends ReactiveCrudRepository<Car, Long> {
  Mono<Car> findByName(String name);
  Flux<Car> findByOwnerId(Long owner);
  Flux<Car> findByOwnerIdIn(List<Long> owner);
}
