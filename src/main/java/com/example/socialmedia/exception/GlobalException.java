package com.example.socialmedia.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {
  
  @ExceptionHandler(UserException.class)
  public ResponseEntity<ErrorDetail> handleUserException(
    UserException ex, WebRequest req
  ){
    ErrorDetail err =  new ErrorDetail(
      ex.getMessage(), req.getDescription(false), LocalDateTime.now());

    return new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(PostException.class)
  public ResponseEntity<ErrorDetail> handlePostException(
    PostException ex, WebRequest req
  ){
    ErrorDetail err =  new ErrorDetail(
      ex.getMessage(), req.getDescription(false), LocalDateTime.now());

    return new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CommentException.class)
  public ResponseEntity<ErrorDetail> handleCommentException(
    CommentException ex, WebRequest req
  ){
    ErrorDetail err =  new ErrorDetail(
      ex.getMessage(), req.getDescription(false), LocalDateTime.now());

    return new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(StoryException.class)
  public ResponseEntity<ErrorDetail> handleStoryException(
    StoryException ex, WebRequest req
  ){
    ErrorDetail err =  new ErrorDetail(
      ex.getMessage(), req.getDescription(false), LocalDateTime.now());

    return new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDetail> handleMethodException(
    MethodArgumentNotValidException ex
  ){
    ErrorDetail err =  new ErrorDetail(
      ex.getBindingResult().getFieldError().getDefaultMessage(), 
      "validation error",
      LocalDateTime.now()
    );

    return new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetail> handleOtherException(
    Exception ex, WebRequest req
  ){
    ErrorDetail err =  new ErrorDetail(
      ex.getMessage(), req.getDescription(false), LocalDateTime.now());

    return new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);
  }
}
