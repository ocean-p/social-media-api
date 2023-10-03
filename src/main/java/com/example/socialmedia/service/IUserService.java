package com.example.socialmedia.service;

import java.util.List;

import com.example.socialmedia.exception.UserException;
import com.example.socialmedia.model.User;

public interface IUserService {
  public User registerUser(User user) throws UserException;
  public User findUserById(long userId) throws UserException;
  public User findUserProfile(String token) throws UserException;
  public User findUserByUsername(String username) throws UserException;
  public String followUser(long userId, long followerId) throws UserException;
  public String unfollowUser(long userId, long followerId) throws UserException;
  public List<User> findUserByIds(List<Long> userIds) throws UserException;
  public List<User> searchUser(String query) throws UserException;
  public User updateUserDetail(User updatedUser, User existingUser) throws UserException;
}
