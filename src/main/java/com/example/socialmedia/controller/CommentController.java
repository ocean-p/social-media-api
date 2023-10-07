package com.example.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialmedia.exception.CommentException;
import com.example.socialmedia.exception.PostException;
import com.example.socialmedia.exception.UserException;
import com.example.socialmedia.model.Comment;
import com.example.socialmedia.model.User;
import com.example.socialmedia.service.ICommentService;
import com.example.socialmedia.service.IUserService;

@RestController
@RequestMapping("/comments")
public class CommentController {
  
  @Autowired
  private ICommentService commentService;

  @Autowired
  private IUserService userService;

  @PostMapping("/create/{postId}")
  public ResponseEntity<Comment> createComment(
    @RequestBody Comment comment,
    @PathVariable long postId,
    @RequestHeader("Authorization") String token
  ) throws UserException, PostException{
    
    User user= userService.findUserProfile(token);
    Comment createdComment = commentService.createComment(comment, postId, user.getId());
    
    return new ResponseEntity<Comment>(createdComment, HttpStatus.CREATED);
  }

  @PutMapping("/like/{commentId}")
  public ResponseEntity<Comment> likeComment(
    @RequestHeader("Authorization") String token,
    @PathVariable long commentId
  ) throws UserException, CommentException{

    User user= userService.findUserProfile(token);
    Comment comment = commentService.likeComment(commentId, user.getId());

    return new ResponseEntity<Comment>(comment, HttpStatus.OK);
  }

  @PutMapping("/unlike/{commentId}")
  public ResponseEntity<Comment> unlikeComment(
    @RequestHeader("Authorization") String token,
    @PathVariable long commentId
  ) throws UserException, CommentException{

    User user= userService.findUserProfile(token);
    Comment comment = commentService.unlikeComment(commentId, user.getId());

    return new ResponseEntity<Comment>(comment, HttpStatus.OK);
  }

  

}
