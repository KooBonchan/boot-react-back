package com.company.card.repository;

import com.company.card.domain.AppUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<AppUser, String> {
  Mono<AppUser> findByUsername(String username);
}
