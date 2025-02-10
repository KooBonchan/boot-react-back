package com.company.card.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginRequest {
  @Schema(description = "Username", example = "admin")
  private String username;
  @Schema(description = "Password", example = "admin")
  private String password;
}
