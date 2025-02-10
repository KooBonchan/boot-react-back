package com.company.card.service;

import com.company.card.domain.AppUser;
import com.company.card.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.logging.Level;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public Mono<AppUser> authenticate(String username, String password){

    log.info("matches: {}", passwordEncoder.matches
      ("admin", "$2a$10$l5di823ONuOSzt3I.LvDS.3jAC2ftS.MPJjQXLASYS1YFzkWOhRTG"));
    return userRepository.findByUsername(username)
      .filter(appUser -> passwordEncoder.matches(password, appUser.getPassword()))
      .switchIfEmpty(Mono.error(new BadCredentialsException("Invalid username or password")))
      .log(this.getClass().getName(), Level.INFO);
  }

}
