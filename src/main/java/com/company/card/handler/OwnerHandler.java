package com.company.card.handler;

import com.company.card.domain.Owner;
import com.company.card.repository.CarRepository;
import com.company.card.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
@Slf4j
public class OwnerHandler {

  private final CarRepository carRepository;
  private final OwnerRepository ownerRepository;

  public Mono<ServerResponse> getOwner(ServerRequest request){
    var data = ownerRepository.findById(1L)
      .flatMap(owner ->
        carRepository
          .findByOwnerId(owner.getId())
          .collectList()
          .map(cars -> { owner.setCars(cars); return owner;})
      );

    return ServerResponse.ok().body(data, Owner.class);
  }
}
