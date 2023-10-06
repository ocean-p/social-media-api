package com.example.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialmedia.exception.PostException;
import com.example.socialmedia.exception.UserException;
import com.example.socialmedia.model.Post;
import com.example.socialmedia.model.User;
import com.example.socialmedia.response.MessageResponse;
import com.example.socialmedia.service.IPostService;
import com.example.socialmedia.service.IUserService;

@RestController
@RequestMapping("/posts")
public class PostController {
  @Autowired
  private IPostService postService;

  @Autowired
  private IUserService userService;

  @PostMapping("/create")
  public ResponseEntity<Post> createPost(
    @RequestBody Post post,
    @RequestHeader("Authorization") String token
  ) throws UserException{
    User user = userService.findUserProfile(token);
    Post createdPost = postService.createPost(post, user.getId());
    return new ResponseEntity<Post>(createdPost, HttpStatus.CREATED);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Post>> findPostByUserId(
    @PathVariable("userId") long userId
  ) throws UserException{
    List<Post> posts = postService.findPostByUserId(userId);
    return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
  }

  @GetMapping("/users/{userIds}")
  public ResponseEntity<List<Post>> findPostsByUserIds(
    @PathVariable("userIds") List<Long> userIds
  ) throws UserException, PostException{
    List<Post> posts = postService.findAllPostByUserIds(userIds);
    return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
  }

  @GetMapping("/{postId}")
  public ResponseEntity<Post> findPostById(@PathVariable("postId") long postId) throws PostException{
    Post post = postService.findPostById(postId);
    return new ResponseEntity<Post>(post, HttpStatus.OK);
  }

  @PutMapping("/like/{postId}")
  public ResponseEntity<Post> likePost(
    @PathVariable long postId,
    @RequestHeader("Authorization") String token
  ) throws UserException, PostException{
    User user = userService.findUserProfile(token);
    Post likedPost = postService.likePost(postId, user.getId());
    return new ResponseEntity<Post>(likedPost, HttpStatus.OK);
  }

  @PutMapping("/unlike/{postId}")
  public ResponseEntity<Post> unlikePost(
    @PathVariable long postId,
    @RequestHeader("Authorization") String token
  ) throws UserException, PostException{
    User user = userService.findUserProfile(token);
    Post unlikedPost = postService.unlikePost(postId, user.getId());
    return new ResponseEntity<Post>(unlikedPost, HttpStatus.OK);
  }

  @DeleteMapping("/{postId}")
  public ResponseEntity<MessageResponse> deletePost(
    @PathVariable long postId,
    @RequestHeader("Authorization") String token
  ) throws UserException, PostException{
    User user = userService.findUserProfile(token);
    String message = postService.deletePost(postId, user.getId());
    MessageResponse res = new MessageResponse(message);
    return new ResponseEntity<MessageResponse>(res, HttpStatus.OK);
  }

  @PutMapping("/save/{postId}")
  public ResponseEntity<MessageResponse> savePost(
    @PathVariable long postId,
    @RequestHeader("Authorization") String token
  ) throws UserException, PostException{
    User user = userService.findUserProfile(token);
    String message = postService.savePost(postId, user.getId());
    MessageResponse res = new MessageResponse(message);
    return new ResponseEntity<MessageResponse>(res, HttpStatus.OK);
  }

  @PutMapping("/unsave/{postId}")
  public ResponseEntity<MessageResponse> unsavePost(
    @PathVariable long postId,
    @RequestHeader("Authorization") String token
  ) throws UserException, PostException{
    User user = userService.findUserProfile(token);
    String message = postService.unsavePost(postId, user.getId());
    MessageResponse res = new MessageResponse(message);
    return new ResponseEntity<MessageResponse>(res, HttpStatus.OK);
  }

}
