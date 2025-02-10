package com.company.card.handler;

import com.company.card.domain.Car;
import com.company.card.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class CarHandler {
  private final CarRepository carRepository;

  public Flux<Car> getCar(){
    return carRepository.findAll();
  }
  public Mono<Car> getCar(Long id){
    return carRepository.findById(id);
  }
  public Mono<Car> getCar(String name){
    return carRepository.findByName(name);
  }
}
