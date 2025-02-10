package com.company.card.handler;

import com.company.card.DTO.LoginRequest;
import com.company.card.service.AuthService;
import com.company.card.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.logging.Level;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthHandler {
  private final AuthService authService;

  public Mono<ServerResponse> login(ServerRequest request){
    return request.bodyToMono(LoginRequest.class)
      .flatMap(req ->
        authService.authenticate(req.getUsername(), req.getPassword()))
      .map(user -> {
        String token = JwtUtil.generateToken(user.getUsername(), user.getRole());
        return ResponseEntity.ok("Bearer " + token);
      })
      .onErrorResume(e ->
        Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password")))
      .log(this.getClass().getName(), Level.INFO)
      .flatMap(response ->
        ServerResponse
          .status(response.getStatusCode())
          .bodyValue(response.getBody())
      );
  }

}
