package com.company.card.controller;

import com.company.card.domain.Car;
import com.company.card.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CarController {
  private final CarService carService;

  @GetMapping("car")
  public Flux<Car> getCar(){
    return carService.getCar();
  }

  @GetMapping("car/{id}")
  public Mono<Car> getCar(int id){
    return carService.getCar(id);
  }
}
