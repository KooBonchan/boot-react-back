package com.company.card.filter;

import com.company.card.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.netty.util.internal.StringUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public class JWTAuthFilter implements WebFilter {
  public static final String HEADER_PREFIX  = "Bearer ";

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

    if(authHeader == null || !authHeader.startsWith(HEADER_PREFIX)){
      return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization header"))
    }
    String token = authHeader.substring(7); // "Bearer "
    
    return chain.filter(exchange);
  }

}
