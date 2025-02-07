package com.company.card.config;

import com.company.card.handler.OwnerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.*;

@Configuration
@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {

  @Bean
  public RouterFunction<ServerResponse> route(OwnerHandler handler){

    return route()
      .build();

//    return RouterFunctions
//      .route(RequestPredicates.GET("/"), handler::getOwner);

  }


  @Override
  public void addCorsMappings(CorsRegistry registry) {
    WebFluxConfigurer.super.addCorsMappings(registry);
  }
}
