package com.example.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialmedia.exception.StoryException;
import com.example.socialmedia.exception.UserException;
import com.example.socialmedia.model.Story;
import com.example.socialmedia.model.User;
import com.example.socialmedia.service.IStoryService;
import com.example.socialmedia.service.IUserService;

@RestController
@RequestMapping("/stories")
public class StoryController {
  
  @Autowired
  private IStoryService storyService;

  @Autowired
  private IUserService userService;

  @PostMapping("/create")
  public ResponseEntity<Story> createStory(
    @RequestBody Story story,
    @RequestHeader("Authorization") String token
  ) throws UserException, StoryException
  {
    User user = userService.findUserProfile(token);
    Story createdStory = storyService.createStory(story, user.getId());
    return new ResponseEntity<Story>(createdStory, HttpStatus.CREATED);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Story>> findAllStoryByUserId(
    @PathVariable long userId
  ) throws UserException, StoryException
  {
    User user = userService.findUserById(userId);
    List<Story> stories = storyService.findStoryByUserId(user.getId());
    return new ResponseEntity<List<Story>>(stories, HttpStatus.OK);
  }

}
