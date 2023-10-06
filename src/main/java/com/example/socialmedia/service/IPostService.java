package com.example.socialmedia.service;

import java.util.List;

import com.example.socialmedia.exception.PostException;
import com.example.socialmedia.exception.UserException;
import com.example.socialmedia.model.Post;

public interface IPostService {

  public Post createPost(Post post, long userId) throws UserException;
  public String deletePost(long postId, long userId) throws UserException, PostException;
  public List<Post> findPostByUserId(long userId) throws UserException;
  public Post findPostById(long postId) throws PostException;
  public List<Post> findAllPostByUserIds(List<Long> userIds) throws UserException, PostException;
  public String savePost(long postId, long userId) throws UserException, PostException;
  public String unsavePost(long postId, long userId) throws PostException, UserException;
  public Post likePost(long postId, long userId) throws UserException, PostException;
  public Post unlikePost(long postId, long userId) throws UserException, PostException;

}
