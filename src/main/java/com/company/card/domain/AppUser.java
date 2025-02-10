package com.company.card.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Table
@Data
@AllArgsConstructor
public class AppUser {
  @Id
  private Long id;

  @NotEmpty
  private String username;

  @NotEmpty
  private String password;
  private String role = "USER";

  public void setPassword(String password){
    this.password = new BCryptPasswordEncoder().encode(password);
  }
}
