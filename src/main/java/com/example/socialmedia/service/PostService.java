package com.example.socialmedia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.socialmedia.dto.UserDto;
import com.example.socialmedia.exception.PostException;
import com.example.socialmedia.exception.UserException;
import com.example.socialmedia.model.Post;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.PostRepository;
import com.example.socialmedia.repository.UserRepository;

@Service
public class PostService implements IPostService {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private IUserService userService;

  @Autowired
  private UserRepository userRepository;

  @Override
  public Post createPost(Post post, long userId) throws UserException {
    User user = userService.findUserById(userId);
    UserDto userDto = new UserDto();
    userDto.setEmail(user.getEmail());
    userDto.setId(user.getId());
    userDto.setName(user.getName());
    userDto.setUserImage(user.getImage());
    userDto.setUsername(user.getUsername());
    
    post.setUser(userDto);

    Post createdPost = postRepository.save(post);

    return createdPost;
  }

  @Override
  public String deletePost(long postId, long userId) throws UserException, PostException {
    Post post = findPostById(postId);
    User user = userService.findUserById(userId);

    if(post.getUser().getId() == user.getId()){
      postRepository.deleteById(post.getId());
      return "Post is deleted.";
    }

    throw new PostException("You can not delete other user's posts");
  }

  @Override
  public List<Post> findPostByUserId(long userId) throws UserException {
    List<Post> posts = postRepository.findByUserId(userId);
    if(posts.size() == 0){
      throw new UserException("This user does not have any post");
    }
    return posts;
  }

  @Override
  public Post findPostById(long postId) throws PostException {
    Optional<Post> opt = postRepository.findById(postId);
    if(opt.isPresent()){
      return opt.get();
    }
    throw new PostException("Post not found with id:" + postId);
  }

  @Override
  public List<Post> findAllPostByUserIds(List<Long> userIds) throws UserException, PostException {
    List<Post> posts = postRepository.findAllPostByUserIds(userIds);
    if(posts.size() == 0){
      throw new PostException("No post available");
    }
    return posts;
  }

  @Override
  public String savePost(long postId, long userId) throws UserException, PostException {
    Post post = findPostById(postId);
    User user = userService.findUserById(userId);
    if(!user.getSavedPosts().contains(post)){
      user.getSavedPosts().add(post);
      userRepository.save(user);
    }
    
    return "Post is saved.";
  }

  @Override
  public String unsavePost(long postId, long userId) throws PostException, UserException {
    Post post = findPostById(postId);
    User user = userService.findUserById(userId);
    if(user.getSavedPosts().contains(post)){
      user.getSavedPosts().remove(post);
      userRepository.save(user);
    }
    
    return "Post is unsaved.";
  }

  @Override
  public Post likePost(long postId, long userId) throws UserException, PostException {
    Post post = findPostById(postId);
    User user = userService.findUserById(userId);

    UserDto userDto = new UserDto();
    userDto.setEmail(user.getEmail());
    userDto.setId(user.getId());
    userDto.setName(user.getName());
    userDto.setUserImage(user.getImage());
    userDto.setUsername(user.getUsername());

    post.getLikedByUsers().add(userDto);
    return postRepository.save(post);
  }

  @Override
  public Post unlikePost(long postId, long userId) throws UserException, PostException {
    Post post = findPostById(postId);
    User user = userService.findUserById(userId);

    UserDto userDto = new UserDto();
    userDto.setEmail(user.getEmail());
    userDto.setId(user.getId());
    userDto.setName(user.getName());
    userDto.setUserImage(user.getImage());
    userDto.setUsername(user.getUsername());

    post.getLikedByUsers().remove(userDto);
    return postRepository.save(post);
  }
  
}
