package com.example.socialmedia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.socialmedia.dto.UserDto;
import com.example.socialmedia.exception.UserException;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.UserRepository;

@Service
public class UserService implements IUserService{

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public User registerUser(User user) throws UserException {
    Optional<User> isEmailExist = userRepository.findByEmail(user.getEmail());
    Optional<User> isUsernameExist = userRepository.findByUsername(user.getUsername());
    if(isEmailExist.isPresent())
      throw new UserException("Email is already used.");
    if(isUsernameExist.isPresent())
      throw new UserException("Username is already used.");
    if(user.getUsername() == null || user.getPassword() == null ||
      user.getName() == null || user.getEmail() == null)
      throw new UserException("Missing required fields.");

    User newUser = new User();
    newUser.setUsername(user.getUsername());
    newUser.setPassword(passwordEncoder.encode(user.getPassword()));
    newUser.setName(user.getName());
    newUser.setEmail(user.getEmail());

    return userRepository.save(newUser);
  }

  @Override
  public User findUserById(long userId) throws UserException {
    Optional<User> userOpt = userRepository.findById(userId);
    if(userOpt.isPresent()) return userOpt.get();

    throw new UserException("User not exist with id:" + userId);
  }

  @Override
  public User findUserProfile(String token) throws UserException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findUserProfile'");
  }

  @Override
  public User findUserByUsername(String username) throws UserException {
    Optional<User> userOpt = userRepository.findByUsername(username);
    if(userOpt.isPresent()) return userOpt.get();

    throw new UserException("User not found with id:" + username);
  }

  @Override
  public String followUser(long userId, long followerId) throws UserException {
    User reqUser = findUserById(userId);
    User followUser = findUserById(followerId);

    UserDto follower = new UserDto();
    follower.setId(reqUser.getId());
    follower.setUsername(reqUser.getUsername());
    follower.setEmail(reqUser.getEmail());
    follower.setName(reqUser.getName());
    follower.setUserImage(reqUser.getImage());

    UserDto following = new UserDto();
    following.setId(followUser.getId());
    following.setUsername(followUser.getUsername());
    following.setEmail(followUser.getEmail());
    following.setName(followUser.getName());
    following.setUserImage(followUser.getImage());

    reqUser.getFollowings().add(following);
    followUser.getFollowers().add(follower);

    userRepository.save(reqUser);
    userRepository.save(followUser);

    return "You are following " + followUser.getUsername();
  }

  @Override
  public String unfollowUser(long userId, long followerId) throws UserException {
    User reqUser = findUserById(userId);
    User followUser = findUserById(followerId);

    UserDto follower = new UserDto();
    follower.setId(reqUser.getId());
    follower.setUsername(reqUser.getUsername());
    follower.setEmail(reqUser.getEmail());
    follower.setName(reqUser.getName());
    follower.setUserImage(reqUser.getImage());

    UserDto following = new UserDto();
    following.setId(followUser.getId());
    following.setUsername(followUser.getUsername());
    following.setEmail(followUser.getEmail());
    following.setName(followUser.getName());
    following.setUserImage(followUser.getImage());

    reqUser.getFollowings().remove(following);
    followUser.getFollowers().remove(follower);

    userRepository.save(reqUser);
    userRepository.save(followUser);

    return "You have unfollowed " + followUser.getUsername();
  }

  @Override
  public List<User> findUserByIds(List<Long> userIds) throws UserException {
    List<User> users = userRepository.findAllUsersByUserIds(userIds);
    return users;
  }

  @Override
  public List<User> searchUser(String query) throws UserException {
    List<User> users = userRepository.findByQuery(query);
    if(users.size() == 0)
      throw new UserException("Not found");

    return users;
  }

  @Override
  public User updateUserDetail(User updatedUser, User existingUser) throws UserException {
    if(updatedUser.getEmail() != null){
      existingUser.setEmail(updatedUser.getEmail());
    }
    if(updatedUser.getBio() != null){
      existingUser.setBio(updatedUser.getBio());
    }
    if(updatedUser.getName() != null){
      existingUser.setName(updatedUser.getName());
    }
    if(updatedUser.getUsername() != null){
      existingUser.setUsername(updatedUser.getUsername());
    }
    if(updatedUser.getMobile() != null){
      existingUser.setMobile(updatedUser.getMobile());
    }
    if(updatedUser.getGender() != null){
      existingUser.setGender(updatedUser.getGender());
    }
    if(updatedUser.getWebsite() != null){
      existingUser.setWebsite(updatedUser.getWebsite());
    }
    if(updatedUser.getImage() != null){
      existingUser.setImage(updatedUser.getImage());
    }

    if(updatedUser.getId() == existingUser.getId()){
      return userRepository.save(existingUser);
    }
    
    throw new UserException("Fail to update!");
  }
  
}
