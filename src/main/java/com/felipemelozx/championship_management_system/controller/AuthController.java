package com.felipemelozx.championship_management_system.controller;

import com.felipemelozx.championship_management_system.dto.user.CreateUserDto;
import com.felipemelozx.championship_management_system.dto.user.FailureResponseDTO;
import com.felipemelozx.championship_management_system.dto.user.LoginDTO;
import com.felipemelozx.championship_management_system.dto.user.SuccessResponseDTO;
import com.felipemelozx.championship_management_system.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final UserService userService;

  public AuthController(UserService repository) {
    this.userService = repository;
  }

  @PostMapping("/register")
  public ResponseEntity<FailureResponseDTO> register(@RequestBody CreateUserDto body){
    var fails = userService.register(body);
    if (fails.isEmpty()){
      URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
          .buildAndExpand(body.email()).toUri();
      return ResponseEntity.created(location).build();
    }
    return ResponseEntity.badRequest().body(new FailureResponseDTO(false, fails));
  }

  @PostMapping("/login")
  public ResponseEntity<SuccessResponseDTO> login(@RequestBody LoginDTO body){
    var token = userService.login(body);
    if(token != null){
      return ResponseEntity.ok().body(new SuccessResponseDTO(true, body.email(),token));
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }
}
