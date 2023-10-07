package com.example.socialmedia.service;

import com.example.socialmedia.exception.CommentException;
import com.example.socialmedia.exception.PostException;
import com.example.socialmedia.exception.UserException;
import com.example.socialmedia.model.Comment;

public interface ICommentService {
  
  public Comment createComment(Comment comment, long postId, long userId)
    throws UserException, PostException;
  
  public Comment findCommentById(long commentId) throws CommentException;

  public Comment likeComment(long commentId, long userId) 
    throws CommentException, UserException;

  public Comment unlikeComment(long commentId, long userId) 
    throws CommentException, UserException;
}
