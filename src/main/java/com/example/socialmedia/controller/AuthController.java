package com.example.socialmedia.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialmedia.exception.UserException;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.UserRepository;
import com.example.socialmedia.service.IUserService;

@RestController
@RequestMapping("/")
public class AuthController {
  @Autowired
  private IUserService userService;

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/signup")
  public ResponseEntity<User> register(@RequestBody User user) throws UserException{
    User newUser = userService.registerUser(user);
    return new ResponseEntity<User>(newUser, HttpStatus.OK);
  }

  @PostMapping("/signin")
  public ResponseEntity<User> login(Authentication auth) throws BadCredentialsException{
    Optional<User> opt = userRepository.findByUsername(auth.getName());
    if(opt.isPresent())
      return new ResponseEntity<User>(opt.get(), HttpStatus.ACCEPTED);

    throw new BadCredentialsException("invalid username and password");
  }

}
