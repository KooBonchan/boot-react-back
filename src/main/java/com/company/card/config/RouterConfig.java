package com.company.card.config;

import com.company.card.DTO.LoginRequest;
import com.company.card.handler.AuthHandler;
import com.company.card.handler.OwnerHandler;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
@OpenAPIDefinition(
  info = @Info(
    title = "Spring Boot Backend with Spring Reactive",
    version = "1",
    description = "Reactive and R2DBC Backend focusing on data stream"
  )
)
@RequiredArgsConstructor
public class RouterConfig implements WebFluxConfigurer {

  @Bean
  @RouterOperation(
    path = "/owner/{id}",
    produces = { MediaType.APPLICATION_JSON_VALUE},
    method = RequestMethod.GET,
    operation = @Operation(
      operationId = "getOwner",
      responses = {
        @ApiResponse(responseCode = "200", description = "successful operation",
          content = @Content(schema = @Schema(implementation = OwnerHandler.class))),
        @ApiResponse(responseCode = "400", description = "Invalid Owner ID supplied"),
        @ApiResponse(responseCode = "404", description = "Owner not found")
      },
      parameters = {
        @Parameter(in = ParameterIn.PATH, name = "id")
      }
    )
  )
  public RouterFunction<ServerResponse> route(OwnerHandler handler){
    return RouterFunctions.route()
      .GET("/owner/{id}", handler::getOwner)
      .build();
  }


  @Bean
  @RouterOperation(
    path = "/auth/token",
    produces = { MediaType.APPLICATION_JSON_VALUE },
    method = RequestMethod.POST,
    operation = @Operation(
      operationId = "login",
      description = "User Authentication using username and password, publishes JWT token",
      requestBody = @RequestBody(
        content = @Content(
          schema = @Schema(implementation = LoginRequest.class)
        )
      )
    )
  )
  public RouterFunction<ServerResponse> authRoute(AuthHandler handler){
    return RouterFunctions.route()
      .POST("/auth/token", handler::login)
      .build();
  }


  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**")
      .allowedOrigins("http://localhost:3000");
  }
}
