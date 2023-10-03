package com.example.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialmedia.exception.UserException;
import com.example.socialmedia.model.User;
import com.example.socialmedia.service.IUserService;

@RestController
@RequestMapping("/api/users")
public class AuthController {
  @Autowired
  private IUserService userService;

  @PostMapping("/signup")
  public ResponseEntity<User> register(@RequestBody User user) throws UserException{
    User newUser = userService.registerUser(user);
    return new ResponseEntity<User>(newUser, HttpStatus.OK);
  }
}
