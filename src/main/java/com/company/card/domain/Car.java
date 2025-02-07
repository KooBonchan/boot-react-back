package com.company.card.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Getter
@AllArgsConstructor
public class Car {
  @Id long id;

  @Setter private String name, brand, model, color, registrationNumber;
  @Setter private int modelYear, price;

  @Setter long ownerId;
}
