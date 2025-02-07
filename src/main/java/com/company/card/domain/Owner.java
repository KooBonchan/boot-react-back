package com.company.card.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table
@Getter
public class Owner {
  @Id Long id;

  @Setter private String firstName, lastName;

  @Transient
  @Setter private List<Car> cars;
}
