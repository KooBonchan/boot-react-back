package com.company.card.config;

import com.company.card.handler.OwnerHandler;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.net.URI;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
@EnableWebFlux
@RequiredArgsConstructor
public class WebFluxConfig implements WebFluxConfigurer {

  @Bean
  OpenAPI databaseOpenAPI() {
    return new OpenAPI()
      .info(new Info()
        .title("TITLE")
        .description("DESCRIP PTION")
        .version("0.0.1")
      );
  }

  @Bean
  @RouterOperations(
    value={
      @RouterOperation(path="/",
        produces = {MediaType.APPLICATION_JSON_VALUE}

      )
    }
  )
  RouterFunction<ServerResponse> route(OwnerHandler handler){

    return RouterFunctions
      .route(GET("/"), handler::getOwner)
      .andRoute(GET("/api-docs"),
        request -> ServerResponse.ok()
          .contentType(MediaType.APPLICATION_JSON)
          .bodyValue(databaseOpenAPI())
      )
      .andRoute(GET("/swagger-ui.html"),
        request -> ServerResponse
          .temporaryRedirect(URI.create("/swagger-ui/index.html"))
          .build()
      );


  }


  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**")
      .allowedOrigins("http://localhost:3000");
  }
}
