package com.felipemelozx.championship_management_system.controller;

import com.felipemelozx.championship_management_system.dto.user.CreateUserDto;
import com.felipemelozx.championship_management_system.dto.user.LoginDTO;
import com.felipemelozx.championship_management_system.dto.user.ResponseDTO;
import com.felipemelozx.championship_management_system.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final UserService userService;

  public AuthController(UserService repository) {
    this.userService = repository;
  }

  @PostMapping("/register")
  public ResponseEntity<List<String>> register(@RequestBody CreateUserDto body){
    var fails = userService.register(body);
    if (fails.isEmpty()){
      URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
          .buildAndExpand(body.email()).toUri();
      return ResponseEntity.created(location).build();
    }
    return ResponseEntity.badRequest().body(fails);
  }

  @PostMapping("/login")
  public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO body){
    var token = userService.login(body);
    if(token != null){
      return ResponseEntity.ok().body(new ResponseDTO(body.email(),token));
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }
}
