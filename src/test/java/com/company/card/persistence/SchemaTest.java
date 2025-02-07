package com.company.card.persistence;


import com.company.card.domain.Car;
import com.company.card.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.Logger;

import java.util.logging.Level;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class SchemaTest {
  @Autowired private CarRepository carRepository;

  @Test
  public void readTest() {
    carRepository.findAll()
      .collect(Collectors.toList())

      .as(StepVerifier::create)
      .expectNextMatches(cs -> !cs.isEmpty())
      .verifyComplete();
  }



}
