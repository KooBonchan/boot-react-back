package com.company.card.legacyMvc.service;


import com.company.card.domain.Owner;
import com.company.card.repository.CarRepository;
import com.company.card.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerService {
  private final CarRepository carRepository;
  private final OwnerRepository ownerRepository;

  public Mono<Owner> getOwner(Long id){
    return ownerRepository.findById(id)
      .flatMap(owner ->
        carRepository
        .findByOwnerId(owner.getId())
        .collectList()
        .map(cars -> { owner.setCars(cars); return owner;})
      );
  }


}
