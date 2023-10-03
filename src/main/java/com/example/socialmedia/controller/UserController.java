package com.example.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialmedia.exception.UserException;
import com.example.socialmedia.model.User;
import com.example.socialmedia.response.MessageResponse;
import com.example.socialmedia.service.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  private IUserService userService;

  @GetMapping("id/{id}")
  public ResponseEntity<User> findUserById(@PathVariable long id) throws UserException{
    User user = userService.findUserById(id);
    return new ResponseEntity<User>(user, HttpStatus.OK);
  }

  @GetMapping("username/{username}")
  public ResponseEntity<User> findUserByUsername(@PathVariable String username) throws UserException{
    User user = userService.findUserByUsername(username);
    return new ResponseEntity<User>(user, HttpStatus.OK);
  }

  @PutMapping("follow/{userId}")
  public ResponseEntity<MessageResponse> followUser(@PathVariable long userId){
    return null;
  }

  @PutMapping("unfollow/{userId}")
  public ResponseEntity<MessageResponse> unfollowUser(@PathVariable long userId){
    return null;
  }

  @PutMapping("/req")
  public ResponseEntity<MessageResponse> findUserProfile(
    @RequestHeader("Authorization") String token
  ){
    return null;
  }

  @GetMapping("/{userIds}")
  public ResponseEntity<List<User>> findUserByUserIds(@PathVariable List<Long> userIds) throws UserException{
    List<User> users = userService.findUserByIds(userIds);
    return new ResponseEntity<List<User>>(users, HttpStatus.OK);
  }

  @GetMapping("/search")
  public ResponseEntity<List<User>> searchUser(
    @RequestParam("q") String query) throws UserException{
    List<User> users = userService.searchUser(query);
    return new ResponseEntity<List<User>>(users, HttpStatus.OK);
  }

  @PutMapping("/update")
  public ResponseEntity<MessageResponse> updateUser(
    @RequestHeader("Authorization") String token,
    @RequestBody User user
  ){
    return null;
  }
}
